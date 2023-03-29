import classNames from "classnames/bind";
import MovieDetail from "~/components/MovieDetail";
import ScheduleDate from "~/components/ScheduleDate";
import styles from "./Movie.module.scss";
import { useEffect, useState } from "react";
import fetchAPI from "~/FetchAPI/fetchAPI";
import { base64StringToBlob } from "blob-util";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowRight, faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import dateInDDMMYYFormat from "~/utils/date/dateInDDMMYYFormat";
import addDay from "~/utils/date/addDay";
import RoomMovieSchedule from "~/components/RoomMovieSchedule";
import { compareDateFormatter } from "~/utils/date/compareDateFormatter";
import localDateConverter from "~/utils/date/localDateConverter";
import { useParams } from "react-router-dom";

const cx = classNames.bind(styles);
function Movie() {
  const date = new Date();
  let { movie_id } = useParams();

  const [movie, setMovie] = useState("");
  const [movieSessions, setMovieSessions] = useState([]);
  const [offset, setOffset] = useState(0);
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [movieSystemList, setMovieSystemList] = useState([]);
  const getAllFilmSchedule = (name, scheduleDate) => {
    const scheduleList = movieSessions.filter((session) => {
      return (
        session.roomDTO.movieSystemDTO.name === name &&
        compareDateFormatter(session.startTime, scheduleDate)
      );
    });
    return scheduleList.map((schedule) => schedule.startTime);
  };
  const getSessionList = (systemName) => {
    return movieSessions.filter((session) => {
      return session.roomDTO.movieSystemDTO.name === systemName;
    });
  };
  useEffect(() => {
    fetchAPI(`http://localhost:8080/api/movies/${movie_id}`, "GET")
      .then((response) => response.json())
      .then((data) => {
        data.thumbnail = URL.createObjectURL(
          base64StringToBlob(data.thumbnail, "image/png")
        );
        setMovie(data);
      })
      .catch((error) => {
        console.log(error);
      });
    fetchAPI(
      `http://localhost:8080/api/movies/${movie_id}/upcoming/sessions?date=${date.toISOString()}`,
      "GET"
    )
      .then((response) => response.json())
      .then((data) => {
        console.log(data);

        data.forEach((session) => {
          session.startTime = localDateConverter(session.startTime);
        });

        setMovieSessions(data);
      })
      .catch((error) => {
        console.log(error);
      });
    fetchAPI("http://localhost:8080/api/movieSystems", "GET")
      .then((response) => response.json())
      .then((data) => {
        setMovieSystemList(data);
      })
      .catch((error) => {
        console.log(error);
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  useEffect(() => {}, [selectedDate]);
  const handleLeftSlide = () => {
    setOffset(offset === 3 ? 0 : 3);
  };
  const handleRightSlide = () => {
    setOffset(offset === 0 ? 3 : 0);
  };
  const handleOnClickScheduleDate = (dateValue) => {
    setSelectedDate(addDay(date, dateValue));
  };

  return (
    <div className={cx("wrapper")}>
      <MovieDetail movie={movie} />
      <div className={cx("schedule-time")}>
        <h3>VUI LÒNG CHỌN THÔNG TIN VÉ</h3>

        <div className={cx("show-time")}>
          <div className={cx("left-arrow")} onClick={handleLeftSlide}>
            <FontAwesomeIcon icon={faArrowLeft} />
          </div>
          {Array.from({ length: 14 }, (_, i) => (
            <ScheduleDate
              onClick={() => {
                handleOnClickScheduleDate(i + offset);
              }}
              selected={
                dateInDDMMYYFormat(selectedDate).toDateString() ===
                dateInDDMMYYFormat(addDay(date, i + offset)).toDateString()
              }
              key={i}
              date={addDay(date, i + offset)}
            />
          ))}
          <div className={cx("right-arrow")} onClick={handleRightSlide}>
            <FontAwesomeIcon icon={faArrowRight} />
          </div>
        </div>
        {movieSystemList.map((movieSystem, index) => {
          return (
            <RoomMovieSchedule
              SessionList={getSessionList(movieSystem.name)}
              key={index}
              movieSystem={movieSystem}
              movieClassify={movie.classify}
              showTimeList={getAllFilmSchedule(movieSystem.name, selectedDate)}
            />
          );
        })}
      </div>
    </div>
  );
}

export default Movie;
