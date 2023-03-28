import classNames from "classnames/bind";
import styles from "./EventSection.module.scss";
import config from "~/config";
import { useState } from "react";
import Image from "~/components/Image";
import ChooseSlide from "~/components/ChooseSlide";
const cx = classNames.bind(styles);
function EventSection() {
  const [slideIndex, setSlideIndex] = useState(0);
  const doubleSlides = [...config.slides, ...config.slides];
  const handleChooseSlide = (index) => {
    setSlideIndex(index % config.slides.length);
  };

  return (
    <div className={cx("wrapper")}>
      <div className={cx("event")}>
        <span className={cx("discount-title")}>KHUYẾN MÃI</span>
        <span className={cx("symbol")}>|</span>
        <span className={cx("event-title")}>SỰ KIỆN</span>
      </div>
      <div className={cx("event-content")}>
        {doubleSlides.slice(slideIndex, slideIndex + 3).map((slide, index) => {
          return (
            <Image
              key={index}
              width="400px"
              height="auto"
              src={config.slides[(index + slideIndex) % config.slides.length]}
              alt=""
              className={cx("sliders")}
            />
          );
        })}
      </div>
      <div className={cx("choose-slide")}>
        {config.slides.map((_, index) => {
          return (
            <ChooseSlide
              selected={index === slideIndex}
              key={index}
              onClick={() => {
                handleChooseSlide(index);
              }}
            />
          );
        })}
      </div>
    </div>
  );
}

export default EventSection;
