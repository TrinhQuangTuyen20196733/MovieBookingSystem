import Input from "~/components/Input";
import classNames from "classnames/bind";
import styles from "./AddPage.module.scss";
import Button from "~/components/Button";
import Image from "~/components/Image";
import { useState, useLayoutEffect } from "react";
import config from "~/config";
import { useLocation } from "react-router-dom";
import fetchAPI from "~/FetchAPI/fetchAPI";
import moment from "moment";

import { base64StringToBlob } from "blob-util";
const cx = classNames.bind(styles);
function AddMovie() {
  const [title, setTitle] = useState("");
  const [thumbnail, setThumbnail] = useState(null);
  const [alt, setAlt] = useState("");
  const [trailer, setTrailer] = useState("");
  const [directorName, setDirectorName] = useState("");
  const [actorName, setActorName] = useState("");
  const [startDate, setStartDate] = useState("");
  const [length, setLength] = useState("");
  const [language, setLanguage] = useState("");
  const [classify, setClassify] = useState("");
  const [type, setType] = useState("");
  const [content, setContent] = useState("");
  const [id, setId] = useState(0);
  const [imageNotUpdated, setImageNotUpdated] = useState();

  const location = useLocation();
  const movie_id = location.state?.movie_id;
  useLayoutEffect(() => {
    if (movie_id) {
      fetchAPI(`http://localhost:8080/admin/movies/${movie_id}`, "GET")
        .then((response) => {
          if (response.status === 401) {
            throw new Error("Bạn không có quyền truy cập đường dẫn này");
          } else if (response.status === 404) {
            throw new Error("Người dùng này không tồn tại");
          }

          return response.json();
        })
        .then((data) => {
          console.log(data);
          setId(data.id);
          setTitle(data.title);
          setAlt(data.alt);
          setTrailer(data.trailer);
          setDirectorName(data.director);
          setActorName(data.actor);
          const start_Date = moment(data.startDate).format("YYYY-MM-DD");
          setStartDate(start_Date);
          setLength(data.length);
          setLanguage(data.language);
          setType(data.type);
          setClassify(data.classify);
          setImageNotUpdated(
            URL.createObjectURL(base64StringToBlob(data.thumbnail, "image/png"))
          );
          setContent(data.shortDescription);
        })
        .catch((error) => {
          alert(error.message);
        });
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  const handleSubmit = () => {
    const jwtToken = localStorage.getItem("jwtToken");
    console.log(thumbnail);
    const movieInfor = {
      id,
      title,
      alt,
      trailer,
      shortDescription: content,
      classify,
      director: directorName,
      actor: actorName,
      type,
      startDate,
      length,
      language,
    };
    if (!movie_id) {
      const formData = new FormData();
      formData.append("image", thumbnail);
      formData.append("movieInfo", JSON.stringify(movieInfor));
      console.log(formData);
      fetch("http://localhost:8080/admin/movies", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
        body: formData,
      })
        .then((response) => {
          if (response.status === 401) {
            throw new Error("Bạn không có quyền truy cập đường dẫn này");
          }
          if (response.status === 200) {
            alert("Bạn đã thêm phim thành công");
          }
          return response.json();
        })
        .then((data) => {
          if (data.description) {
            alert(data.description);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    } else if (thumbnail != null) {
      const formData = new FormData();
      formData.append("image", thumbnail);
      formData.append("movieInfo", JSON.stringify(movieInfor));
      console.log(formData);
      fetch(`http://localhost:8080/admin/movies/${id}`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
        body: formData,
      })
        .then((response) => {
          if (response.status === 401) {
            throw new Error("Bạn không có quyền truy cập đường dẫn này");
          }
          if (response.status === 200) {
            alert("Bạn đã cập nhật phim thành công");
          }
          return response.json();
        })
        .then((data) => {
          if (data.description) {
            alert(data.description);
          }
        })
        .catch((error) => {
          console.log(error.message);
        });
    } else if (thumbnail == null) {
      fetchAPI(
        `http://localhost:8080/admin/noImageUpdate/movies/${id}`,
        "PUT",
        movieInfor
      )
        .then((response) => {
          if (response.status === 401) {
            throw new Error("Bạn không có quyền truy cập đường dẫn này");
          }
          if (response.status === 200) {
            alert("Bạn đã cập nhật phim thành công");
          }
          return response.json();
        })
        .then((data) => {
          if (data.description) {
            alert(data.description);
          }
        })
        .catch((error) => {
          console.log(error.message);
        });
    }
  };

  return (
    <div className={cx("wrapper")}>
      <h1 className={cx("title")}>Thông tin người dùng</h1>
      <div className={cx("container")}>
        <Input
          label="Tiêu đề (*)"
          value={title}
          onChange={(e) => {
            setTitle(e.target.value);
          }}
        />
        {movie_id ? (
          <div className={cx("imageNotUpdated")}>
            <h3>Thumbnail </h3>
            <Image src={imageNotUpdated} width="200px" height="auto" />
          </div>
        ) : (
          <></>
        )}
        <Input
          label="Thumbnail mới(*)"
          type="file"
          onChange={(e) => {
            setThumbnail(e.target.files[0]);
          }}
        />

        <Input
          label="Tên thay thế (*)"
          value={alt}
          onChange={(e) => {
            setAlt(e.target.value);
          }}
        />
        <Input
          label="Trailer (*)"
          value={trailer}
          onChange={(e) => {
            setTrailer(e.target.value);
          }}
        />
        <Input
          label="Tên đạo diễn (*)"
          value={directorName}
          onChange={(e) => {
            setDirectorName(e.target.value);
          }}
        />
        <Input
          label="Tên diễn viên (*)"
          value={actorName}
          onChange={(e) => {
            setActorName(e.target.value);
          }}
        />
        <Input
          label="Ngày bắt đầu (*)"
          type="date"
          defaultValue={startDate}
          onChange={(e) => {
            setStartDate(e.target.value);
          }}
        />
        <Input
          label="Thời lượng (*)"
          type="number"
          value={length}
          onChange={(e) => {
            setLength(e.target.value);
          }}
        />
        <Input
          label="Ngôn ngữ (*)"
          value={language}
          onChange={(e) => {
            setLanguage(e.target.value);
          }}
        />
        <div className={cx("classify-select")}>
          <label htmlFor="classify">Đối tượng xem phim</label>
          <select
            name="classify"
            id="classify"
            value={classify}
            onChange={(e) => {
              setClassify(e.target.value);
            }}
          >
            <option>Phù hợp cho mọi lứa tuổi</option>
            <option>Khán giả từ 12 tuổi trở lên</option>
            <option>Khán giả trên 18 tuổi</option>
          </select>
        </div>
        <div className={cx("type-select")}>
          <label htmlFor="type">Thể loại phim</label>
          <select
            name="type"
            id="type"
            value={type}
            onChange={(e) => {
              setType(e.target.value);
            }}
          >
            <option>Tình cảm</option>
            <option>Phim hài</option>
            <option>Phim Hành động</option>
            <option>Phim kinh dị</option>
            <option>Phim khoa học viễn tưởng</option>
            <option>Phim hoạt hình</option>
          </select>
        </div>

        <div className={cx("content")}>
          <h3>Nội dung</h3>
          <textarea
            className={cx("content-area")}
            value={content}
            onChange={(e) => {
              setContent(e.target.value);
            }}
          >
            {content}
          </textarea>
        </div>

        <div className={cx("button")}>
          <Button
            text
            primaryColor
            to={config.routes.MovieList}
            className={cx("btn-back")}
          >
            Xem danh sách
          </Button>
          <Button
            text
            primaryColor
            className={cx("btn-submit")}
            onClick={handleSubmit}
          >
            Submit
          </Button>
        </div>
      </div>
    </div>
  );
}

export default AddMovie;
