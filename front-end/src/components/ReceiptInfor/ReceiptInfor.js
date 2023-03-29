import classNames from "classnames/bind";
import { useEffect } from "react";
import { useState } from "react";
import styles from "./ReceiptInfor.module.scss";
import fetchAPI from "~/FetchAPI/fetchAPI";
const cx = classNames.bind(styles);
function ReceiptInfor({ receipt }) {
  const [session, setSession] = useState({});
  const [serviceList, setServiceList] = useState([]);

  useEffect(() => {
    fetchAPI(
      `http://localhost:8080/api/sessions/${receipt.seatOnSessionDTOList[0].session_id}`,
      "GET"
    )
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setSession(data);
      })
      .catch((error) => {
        console.log(error);
      });
    fetchAPI(`http://localhost:8080/api/services`, "GET")
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setServiceList(data);
      })
      .catch((error) => {
        console.log(error);
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  const renderService = (receipt) => {
    return receipt.serviceReceiptDTOList.map((service, index) => {
      return (
        <tr key={index}>
          <td className={cx("no")}>{index + 2}</td>
          <td className={cx("email")}>
            {receipt.userDTO.lastName} {receipt.userDTO.firstName}
          </td>
          <td className={cx("site")}>
            {session?.roomDTO?.movieSystemDTO?.name}
          </td>
          <td className={cx("item-name")}>
            {serviceList[service.service_id - 1]?.name}
          </td>
          <td className={cx("quantity")}>{service.count}</td>
          <td className={cx("spend")}>
            {(
              serviceList[service.service_id - 1]?.cost * service.count
            )?.toLocaleString("vi-VN", {
              style: "currency",
              currency: "VND",
            })}
          </td>
          <td className={cx("movie-name")}>{session?.movieDTO?.title}</td>
        </tr>
      );
    });
  };
  return (
    <div className={cx("wrapper")}>
      <h3>TRANSACTION</h3>
      <table className={cx("table")}>
        <thead>
          <tr>
            <th className={cx("no")}>No</th>
            <th className={cx("email")}>Name</th>
            <th className={cx("site")}>Site</th>
            <th className={cx("item-name")}>ItemName</th>
            <th className={cx("quantity")}>Quantity</th>
            <th className={cx("spend")}>Spend</th>
            <th className={cx("movie-name")}>Movie</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td className={cx("no")}>1</td>
            <td className={cx("email")}>
              {receipt.userDTO.lastName} {receipt.userDTO.firstName}
            </td>
            <td className={cx("site")}>
              {" "}
              {session?.roomDTO?.movieSystemDTO?.name}
            </td>
            <td className={cx("item-name")}>Seat</td>
            <td className={cx("quantity")}>
              {receipt.seatOnSessionDTOList.length}
            </td>
            <td className={cx("spend")}>
              {(
                session?.cost * receipt.seatOnSessionDTOList.length
              )?.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
              })}
            </td>
            <td className={cx("movie-name")}>{session?.movieDTO?.title}</td>
          </tr>
          {renderService(receipt)}
        </tbody>
      </table>
    </div>
  );
}

export default ReceiptInfor;
