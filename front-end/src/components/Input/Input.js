import classNames from "classnames/bind";
import styles from "./Input.module.scss";
import "react-datepicker/dist/react-datepicker.css";
import "./YearPicker.css";
const cx = classNames.bind(styles);
function Input({ label, type = "text", name, gender, content, ...props }) {
  const classes = cx("wrapper", {
    content,
  });
  return (
    <div className={classes}>
      <label className={cx("label")}>{label}</label>

      <input type={type} name={name} className={cx("input")} {...props} />
    </div>
  );
}

export default Input;
