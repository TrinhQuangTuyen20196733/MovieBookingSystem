import classNames from "classnames/bind";
import styles from "./PageList.module.scss";

import { useEffect, useState } from "react";

import fetchAPI from "~/FetchAPI/fetchAPI";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  IconButton,
} from "@mui/material";
import ReactPaginate from "react-paginate";
import SearchIcon from "@mui/icons-material/Search";
import ClearIcon from "@mui/icons-material/Clear";

import { TextField, InputAdornment } from "@material-ui/core";
import Button from "~/components/Button";
import config from "~/config";
import { Delete, Edit } from "@mui/icons-material";
import { Link } from "react-router-dom";
import { base64StringToBlob } from "blob-util";
import Image from "~/components/Image";
const cx = classNames.bind(styles);
function MovieList() {
  const [updateState, setUpdateState] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [totalPage, setTotalPage] = useState(20);
  const [currentPage, setCurrentPage] = useState(0);
  const [items, setItems] = useState([]);
  const [searchAction, setSearchAction] = useState(false);
  useEffect(() => {
    if (!searchAction) {
      fetchAPI(`http://localhost:8080/admin/movies/pages/${currentPage}`, "GET")
        .then((response) => response.json())
        .then((data) => {
          data.movieDTOList.forEach((movie) => {
            movie.thumbnail = URL.createObjectURL(
              base64StringToBlob(movie.thumbnail, "image/png")
            );
          });
          console.log(data);
          setItems(data.movieDTOList);
          setTotalPage(data.totalPage);
          console.log(currentPage);
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      fetchAPI(
        `http://localhost:8080/admin/movies/search/pages/${currentPage}?title=${searchTerm}`,
        "GET"
      )
        .then((response) => response.json())
        .then((data) => {
          data.movieDTOList.forEach((movie) => {
            movie.thumbnail = URL.createObjectURL(
              base64StringToBlob(movie.thumbnail, "image/png")
            );
          });
          console.log(data);
          setItems(data.movieDTOList);
          setTotalPage(data.totalPage);
        })
        .catch((error) => console.error(error));
    }

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [updateState, currentPage, searchAction]);
  const handleDelete = (id) => {
    fetchAPI(`http://localhost:8080/admin/movies/${id}`, "DELETE")
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        console.log(id);
        setUpdateState(!updateState);
        if (data.description) {
          alert(data.description);
        }
      })
      .catch((error) => console.log(error));
  };
  const handleSearchTermChange = (e) => {
    setSearchTerm(e.target.value);
  };
  const handlePageClick = (selectedPage) => {
    console.log(currentPage);
    setCurrentPage(selectedPage.selected);
  };
  const handleSearchClick = () => {
    fetchAPI(
      `http://localhost:8080/admin/movies/search/pages/0?title=${searchTerm}`,
      "GET"
    )
      .then((response) => response.json())
      .then((data) => {
        data.movieDTOList.forEach((movie) => {
          movie.thumbnail = URL.createObjectURL(
            base64StringToBlob(movie.thumbnail, "image/png")
          );
        });
        console.log(data);
        setItems(data.movieDTOList);
        setTotalPage(data.totalPage);
        setCurrentPage(0);
        setSearchAction(true);
      })
      .catch((error) => console.error(error));
  };
  const handleClearClick = () => {
    setSearchAction(false);
    setUpdateState(!updateState);
    setCurrentPage(0);
    setSearchTerm("");
  };
  return (
    <div className={cx("wrapper")}>
      <div className={cx("title")}>
        <p>Danh sách phim</p>
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
                  {!searchAction ? (
                    <SearchIcon
                      className={cx("icon")}
                      onClick={handleSearchClick}
                    />
                  ) : (
                    <ClearIcon
                      style={{ color: "red" }}
                      className={cx("icon")}
                      onClick={handleClearClick}
                    />
                  )}
                </InputAdornment>
              ),
            }}
            className={cx("search-bar")}
          />
        </div>
        <Button
          text
          primaryColor
          className={cx("btn-add")}
          to={config.routes.AddMovie}
        >
          Add
        </Button>
      </div>
      <TableContainer component={Paper}>
        <Table classes={{ root: cx("MuiTable-root") }}>
          <TableHead classes={{ root: cx("header") }}>
            <TableRow>
              <TableCell classes={{ root: cx("header-cell") }}>Title</TableCell>
              <TableCell classes={{ root: cx("header-cell") }}>
                Thumnail
              </TableCell>
              <TableCell classes={{ root: cx("header-cell") }}>
                Thể loại
              </TableCell>

              <TableCell classes={{ root: cx("header-cell") }}>
                Ngày công chiếu
              </TableCell>
              <TableCell classes={{ root: cx("header-cell") }}>
                Thời lượng
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {items.map((item) => (
              <TableRow key={item.id}>
                <TableCell classes={{ root: cx("body-cell") }}>
                  {item.title}
                </TableCell>
                <TableCell classes={{ root: cx("body-cell") }}>
                  <Image
                    src={item.thumbnail}
                    alt={item.alt}
                    width="150px"
                    height="auto"
                  />
                </TableCell>
                <TableCell classes={{ root: cx("body-cell") }}>
                  {item.classify}
                </TableCell>

                <TableCell classes={{ root: cx("body-cell") }}>
                  {item.startDate}
                </TableCell>
                <TableCell classes={{ root: cx("body-cell") }}>
                  {item.length}
                </TableCell>

                <TableCell>
                  <Link>
                    <IconButton
                      className={cx("btn-delete")}
                      onClick={() => {
                        handleDelete(item.id);
                      }}
                    >
                      <Delete />
                    </IconButton>
                  </Link>
                  <Link
                    to={config.routes.AddMovie}
                    state={{
                      movie_id: item.id,
                    }}
                  >
                    <IconButton className={cx("btn-edit")}>
                      <Edit />
                    </IconButton>
                  </Link>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <ReactPaginate
        className={cx("pagination")}
        previousLabel={"Previous"}
        nextLabel={"Next"}
        pageCount={totalPage}
        onPageChange={handlePageClick}
        containerClassName={"pagination"}
        activeClassName={cx("active")}
      />
    </div>
  );
}

export default MovieList;
