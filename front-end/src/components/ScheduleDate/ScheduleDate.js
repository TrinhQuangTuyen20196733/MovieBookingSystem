import classNames from "classnames/bind";
import { dayOfWeek } from "~/constant";
import styles from "./Schedule.module.scss";
const cx = classNames.bind(styles);
function ScheduleDate({ date, selected, onClick }) {
  const DateOfWeek = dayOfWeek[date.getDay()];
  const day = date.getDate().toString().padStart(2, "0");
  const month = (date.getMonth() + 1).toString().padStart(2, "0");
  const dateInDDMMFormat = `${day}-${month}`;
  const classes = cx("wrapper", {
    selected,
  });
  return (
    <div className={classes} onClick={onClick}>
      <span className={cx("dateofweek")}>{DateOfWeek}</span>
      <span className={cx("date")}>{dateInDDMMFormat}</span>
    </div>
  );
}

export default ScheduleDate;
