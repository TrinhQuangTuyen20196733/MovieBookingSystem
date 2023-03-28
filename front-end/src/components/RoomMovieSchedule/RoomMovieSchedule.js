import classNames from "classnames/bind";
import { mapLink } from "~/constant";
import styles from "./RoomMovieSchedule.module.scss";
import { classifyCode } from "~/constant";
import getTimeFormatter from "~/utils/date/getTimeFormatter";
import { Link } from "react-router-dom";
import config from "~/config";
const cx = classNames.bind(styles);
function RoomMovieSchedule({
  movieSystem,
  movieClassify,
  showTimeList,
  SessionList,
}) {
  if (SessionList.length > 0) {
    console.log(SessionList);
  }

  const renderShowTime = (showTimeList) => {
    return showTimeList.map((showTime, index) => {
      return (
        <Link
          className={cx("time")}
          key={index}
          to={config.routes.Booking}
          state={{
            session: getSession(showTime)[0],
          }}
        >
          <span className={cx("time-value")}>{getTimeFormatter(showTime)}</span>
        </Link>
      );
    });
  };
  const getSession = (startTime) => {
    return SessionList.filter((session) => {
      return session.startTime === startTime;
    });
  };
  return (
    <div className={cx("wrapper")}>
      <div className={cx("room-infor")}>
        <div className={cx("infor")}>
          <h4 className={cx("room-title")}>{movieSystem.name}</h4>
          <span className={cx("address")}>{movieSystem.address}</span>
        </div>

        <div className={cx("direction")}>
          <a href={mapLink[movieSystem.name]} className={cx("btn-map")}>
            XEM VỊ TRÍ
          </a>
        </div>
      </div>
      <div className={cx("type-and-classify")}>
        <div className={cx("type")}>
          <span>2D</span>
          <span>SUB</span>
        </div>
        <div className={cx("classify")}>
          <span>{classifyCode[movieClassify]}</span>
        </div>
      </div>
      <div className={cx("time-show")}>{renderShowTime(showTimeList)}</div>
    </div>
  );
}

export default RoomMovieSchedule;
