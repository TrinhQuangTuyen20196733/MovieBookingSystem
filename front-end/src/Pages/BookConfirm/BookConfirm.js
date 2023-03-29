import classNames from "classnames/bind";
import styles from "./BookConfirm.module.scss";
import { useLocation } from "react-router-dom";
import SelectService from "~/components/SelectService";
import numberToLetter from "~/utils/numberToLetter";
import Button from "~/components/Button";
import fetchAPI from "~/FetchAPI/fetchAPI";
import { useState } from "react";
import { Link } from "react-router-dom";
import config from "~/config";
const cx = classNames.bind(styles);
function BookConfirm() {
  const [confirmState, setConfirmState] = useState(true);
  const [receiptConfirm, setReceiptConfirm] = useState({});
  const location = useLocation();
  const receipt_infor = location.state?.receipt_infor;
  receipt_infor.seatBook.forEach((seat) => {
    seat.status = true;
  });
  const handleConfirm = () => {
    const serviceReceiptDTOList = receipt_infor.service.map(
      (serviceReceipt) => {
        return {
          id: 0,
          service_id: serviceReceipt.id,
          receipt_id: 0,
          count: serviceReceipt.count,
        };
      }
    );
    const receipt = {
      userDTO: {
        accountDTO: {
          email: receipt_infor.user.email,
        },
      },
      seatOnSessionDTOList: receipt_infor.seatBook,
      serviceReceiptDTOList,
    };

    fetchAPI(`http://localhost:8080/api/receipts`, "POST", receipt)
      .then((response) => {
        if (response.status === 401) {
          throw new Error("Bạn không có quyền truy cập đường dẫn này");
        } else if (response.status === 404) {
          throw new Error("Người dùng này không tồn tại");
        }

        return response.json();
      })
      .then((data) => {
        alert("Bạn đã đặt vé thành công!");
        setConfirmState(false);
        setReceiptConfirm(data);
      })
      .catch((error) => {
        alert(error.message);
      });
  };
  return (
    <div className={cx("wrapper")}>
      <SelectService chooseState chooseFood confirm />
      <div className={cx("container")}>
        <div className={cx("cart-title")}>
          <h2>Giỏ hàng của bạn</h2>
        </div>
        <div className={cx("receipt-infor")}>
          <div className={cx("item-infor")}>
            <p className={cx("green")}>Phim / Title</p>
            <span className={cx("green")}>
              {receipt_infor.session.movieDTO.title}
            </span>
          </div>
          <div className={cx("item-infor")}>
            <p className={cx("red")}>Rạp / Cinema</p>
            <span className={cx("red")}>
              {receipt_infor.session.roomDTO.movieSystemDTO.name}
            </span>
          </div>
          <div className={cx("item-infor")}>
            <p>Phòng / Room</p>
            <span>{receipt_infor.session.roomDTO.name}</span>
          </div>
          <div className={cx("item-infor")}>
            <p>Ghế / Seat</p>
            {receipt_infor.seatBook.map((seat, index) => {
              return (
                <span key={index}>
                  {numberToLetter(seat.seatDTO.row)} - {seat.seatDTO.column}
                </span>
              );
            })}
          </div>
          <div className={cx("item-infor")}>
            <p>Giá vé / Cost</p>
            <span>
              {receipt_infor.session.cost.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
              })}
            </span>
          </div>
          <div className={cx("item-infor")}>
            <p>Ngày / Date</p>
            <span>{receipt_infor.session.startTime.toLocaleString()}</span>
          </div>
          <div className={cx("item-infor")}>
            <p>Thành tiền / total</p>
            <span>{receipt_infor.payment}</span>
          </div>
          <p className={cx("notice")}>
            Quý khách vui lòng kiểm tra lại thông tin trước khi thanh toán
          </p>
          <p className={cx("notice")}>
            Vé mua rồi sẽ không được đổi hoặc trả lại
          </p>
        </div>
        <table className={cx("service")}>
          <thead>
            <tr>
              <th className={cx("head-row")}>Mục</th>
              <th className={cx("head-row")}>Giá</th>
              <th className={cx("head-row")}>Số lượng</th>
              <th className={cx("head-row")}>Thanh toán</th>
            </tr>
          </thead>
          <tbody>
            {receipt_infor.service.map((food, index) => {
              return (
                <tr key={index}>
                  <th className={cx("body-row")}>{food.name}</th>
                  <th className={cx("body-row")}>
                    {food.cost.toLocaleString("vi-VN", {
                      style: "currency",
                      currency: "VND",
                    })}
                  </th>
                  <th className={cx("body-row")}>{food.count}</th>
                  <th className={cx("body-row")}>
                    {(food.count * food.cost).toLocaleString("vi-VN", {
                      style: "currency",
                      currency: "VND",
                    })}
                  </th>
                </tr>
              );
            })}
          </tbody>
        </table>
        <div className={cx("user-infor")}>
          <div className={cx("user-infor-item")}>
            <p className={cx("user-title")}>Tên</p>
            <span>{receipt_infor.user.lastName}</span>
          </div>
          <div className={cx("user-infor-item")}>
            <p className={cx("user-title")}>Email</p>
            <span>{receipt_infor.user.email}</span>
          </div>
        </div>
        {confirmState ? (
          <Button
            type="text"
            primaryColor
            isTicketButton
            className={cx("btn-next")}
            onClick={handleConfirm}
          >
            Xác nhận
          </Button>
        ) : (
          <Link
            to={config.routes.Receipt}
            state={{
              receiptConfirm,
            }}
          >
            <Button
              type="text"
              primaryColor
              isTicketButton
              className={cx("btn-next")}
            >
              Xem hóa đơn của bạn
            </Button>
          </Link>
        )}
      </div>
    </div>
  );
}

export default BookConfirm;
