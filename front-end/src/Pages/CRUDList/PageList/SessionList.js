import classNames from "classnames/bind";
import styles from "./PageList.module.scss";
import { useState } from "react";
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
import { TextField, InputAdornment } from "@material-ui/core";
import Button from "~/components/Button";
import config from "~/config";
import { Delete, Edit } from "@mui/icons-material";
import ReactPaginate from "react-paginate";
import SearchIcon from "@mui/icons-material/Search";
import ClearIcon from "@mui/icons-material/Clear";
import { useEffect } from "react";
import fetchAPI from "~/FetchAPI/fetchAPI";
import { Link } from "react-router-dom";
import localDateConverter from "~/utils/date/localDateConverter";
const cx = classNames.bind(styles);
function SessionList() {
  const [updateState, setUpdateState] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [totalPage, setTotalPage] = useState(20);
  const [currentPage, setCurrentPage] = useState(0);
  const [items, setItems] = useState([]);
  const [searchAction, setSearchAction] = useState(false);
  const handleClearClick = () => {};
  const handleSearchClick = () => {
    setSearchAction(true);
  };
  const handlePageClick = (selectedPage) => {
    setCurrentPage(selectedPage.selected);
  };
  const handleSearchTermChange = (e) => {
    setSearchTerm(e.target.value);
  };
  const handleDelete = (id) => {
    fetchAPI(`http://localhost:8080/admin/sessions/${id}`, "DELETE")
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
  useEffect(() => {
    fetchAPI(`http://localhost:8080/admin/sessions/pages/${currentPage}`, "GET")
      .then((response) => response.json())
      .then((data) => {
        setItems(data.sessionDTOList);
        setTotalPage(data.totalPage);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [currentPage, updateState]);
  return (
    <div className={cx("wrapper")}>
      <div className={cx("title")}>
        <p>Danh sách suất chiếu</p>
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
          to={config.routes.AddMovieSession}
        >
          Add
        </Button>
      </div>
      <TableContainer component={Paper}>
        <Table classes={{ root: cx("MuiTable-root") }}>
          <TableHead classes={{ root: cx("header") }}>
            <TableRow>
              <TableCell classes={{ root: cx("header-cell") }}>
                Tên phim
              </TableCell>
              <TableCell classes={{ root: cx("header-cell") }}>
                Rạp chiếu
              </TableCell>
              <TableCell classes={{ root: cx("header-cell") }}>
                Phòng chiếu
              </TableCell>
              <TableCell classes={{ root: cx("header-cell") }}>
                Giờ bắt đầu
              </TableCell>
              <TableCell classes={{ root: cx("header-cell") }}>
                Giá vé
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {items.map((item) => (
              <TableRow key={item.id}>
                <TableCell classes={{ root: cx("body-cell") }}>
                  {item.movieDTO.title}
                </TableCell>
                <TableCell classes={{ root: cx("body-cell") }}>
                  {item.roomDTO.movieSystemDTO.name}
                </TableCell>
                <TableCell classes={{ root: cx("body-cell") }}>
                  {item.roomDTO.name}
                </TableCell>
                <TableCell classes={{ root: cx("body-cell") }}>
                  {localDateConverter(item.startTime).toLocaleString()}
                </TableCell>
                <TableCell classes={{ root: cx("body-cell") }}>
                  {item.cost.toLocaleString("vi-VN", {
                    style: "currency",
                    currency: "VND",
                  })}
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
                  <Link>
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

export default SessionList;
