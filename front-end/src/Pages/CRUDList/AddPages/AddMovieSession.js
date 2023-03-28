import classNames from "classnames/bind";
import styles from "./AddPage.module.scss";
import { TextField, InputAdornment } from "@material-ui/core";
import { useState, useEffect } from "react";
import SearchIcon from "@mui/icons-material/Search";
import fetchAPI from "~/FetchAPI/fetchAPI";

import { base64StringToBlob } from "blob-util";
import Image from "~/components/Image";

import ClearIcon from "@mui/icons-material/Clear";
import Input from "~/components/Input";
import Button from "~/components/Button";
const cx = classNames.bind(styles);
function AddMovieSession() {
  const [searchTerm, setSearchTerm] = useState("");
  const [movieList, setMovieList] = useState([]);
  const [searchAction, setSearchAction] = useState(false);
  const [movieSelected, setMovieSelected] = useState("");
  const [startTime, setStartTime] = useState("");
  const [movieSystemList, setMovieSystemList] = useState([]);
  const [movieSystem, setMovieSystem] = useState("BHD STAR LONG KHÁNH");
  const [movieRoom, setMovieRoom] = useState("Phòng 1");
  const [id, setId] = useState(0);
  const [cost, setCost] = useState(0);
  const handleSubmit = () => {
    const compactSessionDTO = {
      id,
      movie_id: movieSelected.id,
      startTime: new Date(startTime).toISOString(),
      movieSystem_id: getMovieSystemByName(movieSystem).id,
      roomName: movieRoom,
      cost,
    };

    fetchAPI("http://localhost:8080/admin/sessions", "POST", compactSessionDTO)
      .then((response) => {
        if (response.status === 200) {
          alert("Bạn đã thêm suất chiếu thành công");
        }

        return response.json();
      })
      .then((data) => {
        if (data.description) {
          alert(data.description);
        }
      })
      .catch((error) => {
        alert(error.message);
      });
  };
  const getMovieSystemByName = (name) => {
    return movieSystemList.filter((system) => {
      return system.name === movieSystem;
    })[0];
  };
  const handleSearchTermChange = (e) => {
    setSearchTerm(e.target.value);
  };
  const handleSearchClick = () => {
    fetchAPI(
      `http://localhost:8080/admin/movies/search?title=${searchTerm}`,
      "GET"
    )
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        data.forEach((movie) => {
          movie.thumbnail = URL.createObjectURL(
            base64StringToBlob(movie.thumbnail, "image/png")
          );
        });
        setSearchAction(true);
        setMovieList(data);

        setMovieSelected("");
      })
      .catch((error) => console.error(error));
  };
  const renderMovie = (movieList) => {
    return movieList.map((movie, index) => {
      return (
        <div className={cx("movie-suggest")} key={index}>
          <Image
            src={movie.thumbnail}
            alt={movie.alt}
            width="60px"
            height="auto"
          />
          <span
            className={cx("movie-title")}
            onClick={() => {
              handleMovieClick(movie);
            }}
          >
            {movie.title}
          </span>
        </div>
      );
    });
  };
  const renderMovieSystem = (movieSystemList) => {
    return movieSystemList.map((movieSystem, index) => (
      <option key={index}>{movieSystem.name}</option>
    ));
  };
  const handleClearClick = () => {
    setSearchAction(false);
    setSearchTerm("");
  };
  const handleMovieClick = (movie) => {
    setSearchAction(false);
    setSearchTerm("");
    setMovieSelected(movie);
  };
  useEffect(() => {
    fetchAPI("http://localhost:8080/api/movieSystems", "GET")
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setMovieSystemList(data);
      })
      .catch((error) => console.error(error));
  }, []);
  return (
    <div className={cx("wrapper")}>
      <div className={cx("title-search")}>
        <p>Chọn phim</p>
        {searchAction && (
          <ClearIcon
            style={{ color: "red" }}
            className={cx("icon-clear")}
            onClick={handleClearClick}
          />
        )}
        {!searchAction && (
          <div className={cx("search-area")}>
            <TextField
              label="Search"
              variant="filled"
              size="small"
              fullWidth
              value={searchTerm}
              onChange={handleSearchTermChange}
              InputProps={{
                endAdornment: (
                  <InputAdornment position="start">
                    <SearchIcon
                      className={cx("icon")}
                      onClick={handleSearchClick}
                    />
                  </InputAdornment>
                ),
              }}
              className={cx("search-bar")}
            />
          </div>
        )}
        {searchAction && (
          <div className="movie-container">{renderMovie(movieList)}</div>
        )}
      </div>
      {movieSelected && (
        <div className={cx("selected-movie")}>
          <span>Phim đã chọn</span>
          <span className={cx("selected-title")}>{movieSelected.title}</span>
        </div>
      )}
      <div className={cx("input")}>
        <Input
          label="Thời gian chiếu (*)"
          type="datetime-local"
          defaultValue={startTime}
          onChange={(e) => {
            setStartTime(e.target.value);
          }}
        />
        <div className={cx("movieSystem-select")}>
          <label htmlFor="movieSystem" className={cx("movieSystem-name")}>
            Rạp phim(*)
          </label>
          <select
            name="movieSystem"
            id="movieSystem"
            value={movieSystem}
            onChange={(e) => {
              setMovieSystem(e.target.value);
            }}
          >
            {renderMovieSystem(movieSystemList)}
          </select>
        </div>
        <div className={cx("movieRoom-select")}>
          <label htmlFor="movieRoom" className={cx("movieRoom-name")}>
            Phòng chiếu(*)
          </label>
          <select
            name="movieRoom"
            id="movieRoom"
            value={movieRoom}
            onChange={(e) => {
              setMovieRoom(e.target.value);
            }}
          >
            <option>Phòng 1</option>
            <option>Phòng 2</option>
            <option>Phòng 3</option>
            <option>Phòng 4</option>
            <option>Phòng 5</option>
            <option>Phòng 6</option>
          </select>
        </div>
        <Input
          label="Giá vé (*)"
          type="number"
          value={cost}
          onChange={(e) => {
            setCost(e.target.value);
          }}
        />
      </div>

      <Button
        text
        primaryColor
        className={cx("btn-submit-session")}
        onClick={handleSubmit}
      >
        Submit
      </Button>
    </div>
  );
}

export default AddMovieSession;
