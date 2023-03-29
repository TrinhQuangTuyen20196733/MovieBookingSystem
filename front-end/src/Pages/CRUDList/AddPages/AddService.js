import classNames from "classnames/bind";
import styles from "./AddPage.module.scss";
import { useState } from "react";
import Input from "~/components/Input";
import Button from "~/components/Button";
import config from "~/config";
import fetchAPI from "~/FetchAPI/fetchAPI";
import { useLocation } from "react-router-dom";
const cx = classNames.bind(styles);

function AddService() {
  const location = useLocation();
  const service = location.state?.service;
  const [name, setName] = useState(service?.name);
  const [code, setCode] = useState(service?.code);
  const [cost, setCost] = useState(service?.cost);
  // eslint-disable-next-line no-unused-vars
  const [id, setId] = useState(service?.id);

  const handleSubmit = () => {
    const service_infor = {
      name,
      code,
      cost,
      id,
    };
    console.log(service_infor);
    if (!id) {
      fetchAPI("http://localhost:8080/admin/services", "POST", service_infor)
        .then((response) => {
          if (response.status === 401) {
            throw new Error("Bạn không có quyền truy cập đường dẫn này");
          }
          if (response.status === 200) {
            alert("Bạn đã thêm dịch vụ thành công");
          }

          return response.json();
        })
        .then((data) => {
          console.log(data);
          if (data.description) {
            alert(data.description);
          }
        })
        .catch((error) => {
          alert(error.message);
        });
    } else {
      fetchAPI(
        `http://localhost:8080/admin/services/${id}`,
        "PUT",
        service_infor
      )
        .then((response) => {
          if (response.status === 401) {
            throw new Error("Bạn không có quyền truy cập đường dẫn này");
          }
          if (response.status === 200) {
            alert("Bạn đã sửa dịch vụ thành công");
          }

          return response.json();
        })
        .then((data) => {
          console.log(data);
          if (data.description) {
            alert(data.description);
          }
        })
        .catch((error) => {
          alert(error.message);
        });
    }
  };
  return (
    <div className={cx("wrapper")}>
      <h1 className={cx("title")}>Thông tin người dùng</h1>
      <div className={cx("container")}>
        <Input
          label="Tên đồ ăn, nước uống(*)"
          type="text"
          value={name}
          onChange={(e) => {
            setName(e.target.value);
          }}
        />
        <Input
          label="Mã code(*)"
          type="text"
          value={code}
          onChange={(e) => {
            setCode(e.target.value);
          }}
        />
        <Input
          label="Giá  (*)"
          type="number"
          value={cost}
          onChange={(e) => {
            setCost(e.target.value);
          }}
        />
        <div className={cx("button")}>
          <Button
            text
            primaryColor
            to={config.routes.ServiceList}
            className={cx("btn-back-service")}
          >
            Xem danh sách
          </Button>
          <Button
            text
            primaryColor
            className={cx("btn-submit-service")}
            onClick={handleSubmit}
          >
            Submit
          </Button>
        </div>
      </div>
    </div>
  );
}

export default AddService;
