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
const cx = classNames.bind(styles);
function ServiceList() {
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
    fetchAPI(`http://localhost:8080/admin/services/${id}`, "DELETE")
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setUpdateState(!updateState);
        if (data.description) {
          alert(data.description);
        }
      })
      .catch((error) => console.log(error));
  };
  useEffect(() => {
    fetchAPI(`http://localhost:8080/admin/services/pages/${currentPage}`, "GET")
      .then((response) => response.json())
      .then((data) => {
        setItems(data.serviceDTOList);
        setTotalPage(data.totalPage);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [currentPage, updateState]);
  return (
    <div className={cx("wrapper")}>
      <div className={cx("title")}>
        <p>Danh sách đồ ăn và nước uống</p>
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
          to={config.routes.AddService}
        >
          Add
        </Button>
      </div>
      <TableContainer component={Paper}>
        <Table classes={{ root: cx("MuiTable-root") }}>
          <TableHead classes={{ root: cx("header") }}>
            <TableRow>
              <TableCell classes={{ root: cx("header-cell") }}>
                Đồ ăn và nước uống
              </TableCell>
              <TableCell classes={{ root: cx("header-cell") }}>
                Giá thành
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {items.map((item) => (
              <TableRow key={item.id}>
                <TableCell classes={{ root: cx("body-cell") }}>
                  {item.name}
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
                  <Link
                    to={config.routes.AddService}
                    state={{
                      service: item,
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

export default ServiceList;
