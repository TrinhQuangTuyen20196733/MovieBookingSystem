import classNames from "classnames/bind";
import styles from "./MovieInfo.module.scss";
import Image from "../Image";
import Button from "../Button/Button";
import { Link } from "react-router-dom";
const cx = classNames.bind(styles);
function MovieInfo({ movie }) {
  return (
    <div className={cx("wrapper")}>
      <Image
        className={cx("image")}
        width="200px"
        height="300px"
        src={movie.thumbnail}
        alt={movie.alt}
        customFallBack={movie.customFallBack}
      />
      <div className={cx("movie-title-wrapper")}>
        <span className={cx("movie-title")}>{movie.title}</span>
      </div>
      <Link to={`/movie/${movie.id}`}>
        <Button
          text
          primaryColor
          isTicketButton
          className={cx("ticket-button")}
        >
          MUA VÉ
        </Button>
      </Link>
    </div>
  );
}

export default MovieInfo;
