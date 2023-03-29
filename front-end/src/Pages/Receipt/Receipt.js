import classNames from "classnames/bind";
import styles from "./Receipt.module.scss";

import { useLocation } from "react-router-dom";
import ReceiptInfor from "~/components/ReceiptInfor";
import { Link } from "react-router-dom";
import Button from "~/components/Button";
import config from "~/config";
const cx = classNames.bind(styles);
function Receipt() {
  const location = useLocation();
  const receipt = location.state?.receiptConfirm;
  console.log(receipt);
  return (
    <div className={cx("wrapper")}>
      <h2>Thông tin hóa đơn của bạn</h2>
      <ReceiptInfor receipt={receipt} />
      <Link className={cx("time")} to={config.routes.Home}>
        <Button
          type="text"
          primaryColor
          isTicketButton
          className={cx("btn-next")}
        >
          Quay về trang chủ
        </Button>
      </Link>
    </div>
  );
}

export default Receipt;
