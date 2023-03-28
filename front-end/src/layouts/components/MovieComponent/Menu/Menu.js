import classNames from "classnames/bind";
import styles from "./Menu.module.scss";
const cx = classNames.bind(styles);
function Menu() {
  return (
    <div className={cx("wrapper")}>
      <h3 className={cx("menu-title")}>PHIM ĐANG CHIẾU</h3>
      <h3 className={cx("menu-title")}>PHIM SẮP CHIẾU</h3>
      <h3 className={cx("menu-title")}>VÉ BÁN TRƯỚC</h3>
    </div>
  );
}

export default Menu;
