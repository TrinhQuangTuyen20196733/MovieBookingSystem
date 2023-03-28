import classNames from "classnames/bind";
import styles from "./SelectService.module.scss";
const cx = classNames.bind(styles);
function SelectService({ chooseState, chooseFood, confirm }) {
  return (
    <div className={cx("wrapper")}>
      <span className={cx("selected")}>
        <span>1. Chọn vé</span>
      </span>
      <span
        className={cx("disable", {
          chooseState,
        })}
      >
        <span>2. Chọn ghế</span>
      </span>
      <span
        className={cx("disable", {
          chooseFood,
        })}
      >
        <span>3. Chọn đồ ăn</span>
      </span>
      <span
        className={cx("disable", {
          confirm,
        })}
      >
        <span>4. Xác nhận</span>
      </span>
      <span className={cx("disable")}>
        <span>5. Đặt vé thành công</span>
      </span>
    </div>
  );
}

export default SelectService;
