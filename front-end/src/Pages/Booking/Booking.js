import classNames from "classnames/bind";
import styles from "./Booking.module.scss";

import { useLocation } from "react-router-dom";
import { useState } from "react";
import SelectService from "~/components/SelectService";
import Image from "~/components/Image";
import { faMinus, faPlus, faXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Button from "~/components/Button";
import MovieSeatPicker from "~/components/MovieSeatPicker";
import fetchAPI from "~/FetchAPI/fetchAPI";
import getAuthentication from "~/utils/getAuthentication";
import config from "~/config";
import { Link } from "react-router-dom";
const cx = classNames.bind(styles);
function Booking() {
  const user = getAuthentication();
  const location = useLocation();
  // eslint-disable-next-line no-unused-vars
  const [session, setSession] = useState(location.state?.session);
  const [standAmount, setStandAmount] = useState(0);
  const [vipAmount, setVipAmount] = useState(0);
  const [chooseState, setChooseState] = useState(false);
  const [totalSeat, setTotalSeat] = useState(0);

  const [seatOnSession, setSeatOnSession] = useState([]);
  const [chooseFood, setChooseFood] = useState(false);
  const [serviceList, setServiceList] = useState([]);
  const [update, setUpdate] = useState(false);
  const [seatBook, setSeatBook] = useState([]);
  const handleMinusStandClick = () => {
    if (standAmount > 0) {
      setStandAmount(standAmount - 1);
    }
  };
  const handleMinusVipClick = () => {
    if (vipAmount > 0) {
      setVipAmount(vipAmount - 1);
    }
  };
  const handlePluStandClick = () => {
    setStandAmount(standAmount + 1);
  };
  const handlePlusVipClick = () => {
    setVipAmount(vipAmount + 1);
  };
  const handleMinusServiceClick = (service) => {
    if (service.count > 0) {
      service.count -= 1;
    }
    setUpdate(!update);
  };
  const handlePlusServiceClick = (service) => {
    service.count += 1;
    setUpdate(!update);
  };

  const getPayment = () => {
    const payment = standAmount * session.cost + vipAmount * session.cost;
    return serviceList.reduce(
      (accumulator, service) => accumulator + service.count * service.cost,
      payment
    );
  };
  const handleChooseClick = () => {
    setChooseState(true);
    setTotalSeat(standAmount + vipAmount);
    fetchAPI(
      `http://localhost:8080/api/sessions/${session.id}/seatOnSessions`,
      "GET"
    )
      .then((response) => response.json())
      .then((data) => {
        setSeatOnSession(data);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  const handleCancelStandClick = () => {
    setStandAmount(0);
    setVipAmount(0);
    setTotalSeat(0);
    setChooseState(false);
    setChooseFood(false);
  };
  const handleChooseFoodClick = (seatBooked) => {
    setSeatBook(seatBooked);
    setChooseFood(true);
    fetchAPI(`http://localhost:8080/api/services`, "GET")
      .then((response) => response.json())
      .then((data) => {
        setServiceList(
          data.map((service) => {
            return {
              ...service,
              count: 0,
            };
          })
        );
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getServiceList = (serviceList) => {
    return serviceList.filter((service) => {
      return service.count > 0;
    });
  };
  const getSeatBook = (seatBook, seatOnSession) => {
    return seatBook.map((seat) => {
      return seatOnSession.filter((seatSession) => {
        return (
          seatSession.seatDTO.row === seat.row &&
          seatSession.seatDTO.column === seat.column &&
          !seatSession.seatDTO.status
        );
      })[0];
    });
  };
  const renderService = (serviceList) => {
    return serviceList.map((service, index) => {
      return (
        <div className={cx("food-item")} key={index}>
          <p className={cx("combo")}>{service.name}</p>
          <p className={cx("food-cost")}>
            {service.cost.toLocaleString("vi-VN", {
              style: "currency",
              currency: "VND",
            })}
          </p>
          <div className={cx("choose-food-item")}>
            <div className={cx("sub")}>
              <FontAwesomeIcon
                icon={faMinus}
                onClick={() => {
                  handleMinusServiceClick(service);
                }}
              />
            </div>

            <div className={cx("cost-value")}>
              <span>{service.count}</span>
            </div>
            <div className={cx("plus")}>
              <FontAwesomeIcon
                icon={faPlus}
                onClick={() => {
                  handlePlusServiceClick(service);
                }}
              />
            </div>
          </div>
        </div>
      );
    });
  };
  const renderFoodCart = () => {
    // eslint-disable-next-line array-callback-return
    return serviceList.map((service, index) => {
      if (service.count > 0) {
        return (
          <div className={cx("cost-card")} key={index}>
            <span className={cx("seat-type-card")}>{service.name}</span>
            <span className={cx("amount")}>{service.count}</span>
            <span className={cx("cost-total-card")}>
              {(service.count * service.cost).toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
              })}
            </span>
          </div>
        );
      }
    });
  };

  return (
    <div className={cx("wrapper")}>
      <SelectService chooseState={chooseState} chooseFood={chooseFood} />
      <div className={cx("container")}>
        <div className={cx("movie-infor")}>
          <Image
            src={session.movieDTO.thumbnail}
            alt={session.movieDTO.alt}
            width="150px"
            height="auto"
          />
          <div className={cx("movie-content")}>
            <h4>{session.movieDTO.title}</h4>
            <span>Showing on {session.startTime.toLocaleString()}</span>
            <span>
              {session.roomDTO.movieSystemDTO.name} - {session.roomDTO.name}
            </span>
          </div>
        </div>

        <div className={cx("order-details")}>
          <h2>GIỎ HÀNG CỦA BẠN</h2>
          <div className={cx("cost-movie")}>
            <div className={cx("movie-action")}>
              <span className={cx("movie-title")}>
                {session.movieDTO.title}
              </span>
              {standAmount + vipAmount > 0 && (
                <FontAwesomeIcon
                  icon={faXmark}
                  onClick={handleCancelStandClick}
                  className={cx("Xmark-icon")}
                />
              )}
            </div>
            <span>
              {(
                standAmount * session.cost +
                vipAmount * session.cost
              ).toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
              })}
            </span>
          </div>
          {standAmount > 0 && (
            <div className={cx("cost-card")}>
              <span className={cx("seat-type-card")}>Adult-Stand-2D</span>
              <span className={cx("amount")}>{standAmount}</span>
              <span className={cx("cost-total-card")}>
                {(standAmount * session.cost).toLocaleString("vi-VN", {
                  style: "currency",
                  currency: "VND",
                })}
              </span>
            </div>
          )}
          {vipAmount > 0 && (
            <div className={cx("cost-card")}>
              <span className={cx("seat-type-card")}>Adult-Vip-2D</span>
              <span className={cx("amount")}>{vipAmount}</span>
              <span className={cx("cost-total-card")}>
                {(vipAmount * session.cost).toLocaleString("vi-VN", {
                  style: "currency",
                  currency: "VND",
                })}
              </span>
            </div>
          )}
          {chooseFood && renderFoodCart()}
          <div className={cx("total-cost")}>
            <span>Tổng cộng</span>
            <span>
              VND
              {getPayment().toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
              })}
            </span>
          </div>
        </div>
      </div>
      {!chooseState && !chooseFood && (
        <>
          <div className={cx("notice")}>
            <h3>LƯU Ý</h3>
            <p>- Hãy chọn kỹ loại vé và số lượng bạn muốn mua</p>
            <p>- Vui lòng chọn kỹ loại vé và số lượng quý khách muốn mua</p>
            <p>
              - Để có trải nghiệm mua vé tốt nhất xin vui lòng sử dụng
              <span> App BHDStar</span>
            </p>
          </div>
          <div className={cx("select-ticket")}>
            <div className={cx("standard-title")}>
              <p>Standard</p>
            </div>
            <div className={cx("ticket-and-seat")}>
              <div className={cx("ticket-container")}>
                <div className={cx("column-title")}>
                  <h6>Vé</h6>
                  <h6>Giá</h6>
                  <h6>Số lượng</h6>
                  <h6>Tổng</h6>
                </div>
                <ul className={cx("ticket-items")}>
                  <div className={cx("row-item")}>
                    <div className={cx("seat-type")}>Adult-Stand-2D</div>
                    <div className={cx("cost")}>
                      {session.cost.toLocaleString("vi-VN", {
                        style: "currency",
                        currency: "VND",
                      })}
                    </div>
                    <div className={cx("choose-ticket")}>
                      <div className={cx("sub")}>
                        <FontAwesomeIcon
                          icon={faMinus}
                          onClick={handleMinusStandClick}
                        />
                      </div>

                      <div className={cx("cost-value")}>
                        <span>{standAmount}</span>
                      </div>
                      <div className={cx("plus")}>
                        <FontAwesomeIcon
                          icon={faPlus}
                          onClick={handlePluStandClick}
                        />
                      </div>
                    </div>
                    <div className={cx("total")}>
                      {(session.cost * standAmount).toLocaleString("vi-VN", {
                        style: "currency",
                        currency: "VND",
                      })}
                    </div>
                  </div>
                  <div className={cx("row-item")}>
                    <div className={cx("seat-type")}>Adult-Vip-2D</div>
                    <div className={cx("cost")}>
                      {session.cost.toLocaleString("vi-VN", {
                        style: "currency",
                        currency: "VND",
                      })}
                    </div>
                    <div className={cx("choose-ticket")}>
                      <div className={cx("sub")}>
                        <FontAwesomeIcon
                          icon={faMinus}
                          onClick={handleMinusVipClick}
                        />
                      </div>

                      <div className={cx("cost-value")}>
                        <span>{vipAmount}</span>
                      </div>
                      <div className={cx("plus")}>
                        <FontAwesomeIcon
                          icon={faPlus}
                          onClick={handlePlusVipClick}
                        />
                      </div>
                    </div>
                    <div className={cx("total")}>
                      {(session.cost * vipAmount).toLocaleString("vi-VN", {
                        style: "currency",
                        currency: "VND",
                      })}
                    </div>
                  </div>
                </ul>
              </div>
              {(standAmount > 0 || vipAmount > 0) && (
                <Button
                  type="text"
                  primaryColor
                  isTicketButton
                  className={cx("choose-seat")}
                  onClick={handleChooseClick}
                >
                  Chọn ghế
                </Button>
              )}
            </div>
          </div>
        </>
      )}

      {chooseState && !chooseFood && (
        <div className={cx("seat-movie")}>
          <h2>Chọn ghế</h2>
          <p className={cx("instructor")}>
            Vui lòng chọn ghế trong sơ đồ ghế phía dưới. Nếu bạn muốn chọn loại
            ghế khác hoặc thay đổi số lượng vé muốn mua vui lòng nhấp vào
            <b> Bước 1 "Chọn Vé"</b>
            ở thanh công cụ bên trên để quay trở lại màn hình chọn.
          </p>
          <div className={cx("seat-area")}>
            <MovieSeatPicker
              seatNumber={totalSeat}
              seatOnSession={seatOnSession}
              onComplete={handleChooseFoodClick}
            />
          </div>
        </div>
      )}
      {chooseFood && (
        <div className={cx("food-area")}>
          <h2>Chọn thực phẩm</h2>
          <p className={cx("food-instruction")}>
            Chọn thực phẩm sau đó kiểm tra lại
          </p>
          <div className={cx("food-booking")}>
            <div className={cx("concession")}>
              <span>Concession</span>
            </div>
            <div className={cx("food-container")}>
              {renderService(serviceList)}
            </div>
          </div>
          <Link
            className={cx("time")}
            to={config.routes.BookConfirm}
            state={{
              receipt_infor: {
                user,
                session,
                seatBook: getSeatBook(seatBook, seatOnSession),
                service: getServiceList(serviceList),
                payment: getPayment().toLocaleString("vi-VN", {
                  style: "currency",
                  currency: "VND",
                }),
              },
            }}
          >
            <Button
              type="text"
              primaryColor
              isTicketButton
              className={cx("btn-next")}
            >
              Kế tiếp
            </Button>
          </Link>
        </div>
      )}
    </div>
  );
}

export default Booking;
