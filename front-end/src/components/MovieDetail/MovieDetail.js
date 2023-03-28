import classNames from "classnames/bind";
import styles from "./MovieDetail.module.scss";
import Image from "../Image";
import Button from "../Button";
const cx = classNames.bind(styles);

function MovieDetail({ movie }) {
  return (
    <div className="wrapper">
      <h3 className={cx("title")}>Trang chủ | {movie.title}</h3>
      <div className={cx("container")}>
        <div className={cx("avatar")}>
          <Image
            src={movie.thumbnail}
            alt={movie.alt}
            width="380px"
            height="auto"
          />
        </div>
        <div className={cx("content")}>
          <h3 className={cx("title")}>{movie.title}</h3>
          <p className={cx("short-description")}>{movie.shortDescription}</p>
          <div className={cx("infor-container")}>
            <div className={cx("infor-detail")}>
              <p className={cx("infor-title")}>Phân loại</p>
              <p>{movie.classify}</p>
            </div>
            <div className={cx("infor-detail")}>
              <p className={cx("infor-title")}>Phân loại</p>
              <p className={cx("type")}>Phim dành cho khán giả {movie.type}</p>
            </div>
            <div className={cx("infor-detail")}>
              <p className={cx("infor-title")}>Đạo diễn</p>
              <p>{movie.director}</p>
            </div>
            <div className={cx("infor-detail")}>
              <p className={cx("infor-title")}>Diễn viên</p>
              <p>{movie.actor}</p>
            </div>
            <div className={cx("infor-detail")}>
              <p className={cx("infor-title")}>Thể loại</p>
              <p>{movie.classify}</p>
            </div>
            <div className={cx("infor-detail")}>
              <p className={cx("infor-title")}>khởi chiếu</p>
              <p>{movie.startDate}</p>
            </div>
            <div className={cx("infor-detail")}>
              <p className={cx("infor-title")}>Thời lượng</p>
              <p>{movie.length} phút</p>
            </div>
            <div className={cx("infor-detail")}>
              <p className={cx("infor-title")}>Ngôn ngữ</p>
              <p>{movie.language}</p>
            </div>
          </div>
          <div className={cx("button")}>
            <Button primaryColor text isTicketButton>
              XEM TRAILER
            </Button>
            <Button primaryColor text isTicketButton>
              MUA VÉ NGAY
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MovieDetail;
