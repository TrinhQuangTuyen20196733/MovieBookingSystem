import classNames from "classnames/bind";
import styles from "./BookConfirm.module.scss";
import { useLocation } from "react-router-dom";
import SelectService from "~/components/SelectService";
import numberToLetter from "~/utils/numberToLetter";
import Button from "~/components/Button";
const cx = classNames.bind(styles);
function BookConfirm() {
  const location = useLocation();
  const receipt_infor = location.state?.receipt_infor;
  console.log(receipt_infor);
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
        <Button
          type="text"
          primaryColor
          isTicketButton
          className={cx("btn-next")}
        >
          Xác nhận
        </Button>
      </div>
    </div>
  );
}

export default BookConfirm;
