import classNames from "classnames/bind";
import styles from "./Menu.module.scss";
import { forwardRef } from "react";
const cx = classNames.bind(styles);
const Menu = forwardRef(({ children, title, onClick }, ref) => {
  return (
    <div className={cx("wrapper")} ref={ref} onClick={onClick}>
      <div className={cx("menu-icon")}>{children}</div>
      <span className={cx("title")}>{title}</span>
    </div>
  );
});

export default Menu;
