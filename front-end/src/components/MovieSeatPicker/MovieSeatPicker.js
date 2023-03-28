import classNames from "classnames/bind";
import styles from "./MovieSeatPicker.scss";
import images from "~/assets/images";
import { useEffect, useState } from "react";
import numberToLetter from "~/utils/numberToLetter";
import Button from "../Button";

const cx = classNames.bind(styles);
function MovieSeatPicker({ seatNumber, seatOnSession, onComplete }) {
  const [selectedList, setSelectedList] = useState([]);

  const [soldList, setSoldList] = useState([]);

  const checkSeat = (rowSeat, columnSeat, seatList) => {
    return (
      seatList.filter((seat) => {
        return seat.row === rowSeat && seat.column === columnSeat;
      }).length > 0
    );
  };
  const handleOnClick = (rowSeat, columnSeat, selectedList) => {
    if (checkSeat(rowSeat, columnSeat, selectedList)) {
      const sList = selectedList.filter((seat) => {
        return seat.row !== rowSeat || seat.column !== columnSeat;
      });
      setSelectedList(sList);
    } else {
      if (selectedList.length === seatNumber) {
        alert("Bạn đang chọn quá số ghế !");
      } else {
        const sList = [...selectedList, { row: rowSeat, column: columnSeat }];
        setSelectedList(sList);
      }
    }
  };
  const handleOnComplete = (selectedList) => {
    if (selectedList.length < seatNumber) {
      alert("Bạn đang chọn thiếu ghế! Vui lòng chọn đủ ghế!");
    } else {
      onComplete(selectedList);
    }
  };
  const render = () => {
    const seatList = [];
    for (let i = 0; i < 6; i++) {
      seatList.push(
        <div className={cx("row-seat")} key={`${i}`}>
          {numberToLetter(i + 1)}
        </div>
      );

      for (let j = 0; j < 8; j++) {
        seatList.push(
          <div
            className={cx("seat", {
              sold: checkSeat(i + 1, j + 1, soldList),
              selected: checkSeat(i + 1, j + 1, selectedList),
            })}
            key={`${i}${j}`}
            onClick={() => {
              handleOnClick(i + 1, j + 1, selectedList);
            }}
          >
            {j + 1}
          </div>
        );
      }
      seatList.push(
        <div className={cx("row-seat")} key={`${i}${i}${i}`}>
          {numberToLetter(i + 1)}
        </div>
      );
    }
    return seatList;
  };
  useEffect(() => {
    const sList = seatOnSession.filter((seat) => {
      return seat.status === true;
    });
    const result = sList.map((seat) => {
      return {
        row: seat.seatDTO.row,
        column: seat.seatDTO.column,
      };
    });
    setSoldList(result);
  }, [seatOnSession]);
  return (
    <div className={cx("wrapper")}>
      <div className={cx("annotation")}>
        <div className={cx("seat-note")}>
          <img
            src={images.SELECTING}
            alt="Ghế đang chọn"
            width="25px"
            height="25px"
          />
          <span>Ghế đang chọn</span>
        </div>
        <div className={cx("seat-note")}>
          <img src={images.EMPTY} alt="Ghế trống" width="25px" height="25px" />
          <span>Ghế trống</span>
        </div>
        <div className={cx("seat-note")}>
          <img src={images.SOLD} alt="Ghế đã bán" width="25px" height="25px" />
          <span>Ghế đã bán</span>
        </div>
      </div>

      <div className={cx("theater")}>
        <div className={cx("screen")}>SCREEN</div>
        <div className={cx("seat-area")}>{render()}</div>
      </div>
      <Button
        type="text"
        primaryColor
        isTicketButton
        className={cx("choose-food")}
        onClick={() => {
          handleOnComplete(selectedList);
        }}
      >
        Kế tiếp
      </Button>
    </div>
  );
}

export default MovieSeatPicker;
