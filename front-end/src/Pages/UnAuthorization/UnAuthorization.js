import classNames from "classnames/bind";
import styles from "./UnAuthorization.module.scss";
const cx = classNames.bind(styles);
function UnAuthorization() {
  return (
    <div className={cx("wrapper")}>
      <h1> Bạn không có quyền truy cập vào trang này!</h1>
    </div>
  );
}

export default UnAuthorization;
