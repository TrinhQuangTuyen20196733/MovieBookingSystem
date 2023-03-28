import classNames from "classnames/bind";
import styles from "./IntroSection.module.scss";
import { useState } from "react";
import Image from "~/components/Image";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowRight, faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import ChooseSlide from "~/components/ChooseSlide";
import config from "~/config";
const cx = classNames.bind(styles);
function IntroSection() {
  const [slideIndex, setSlideIndex] = useState(0);
  const handleLeftSlide = () => {
    if (slideIndex > 0) {
      setSlideIndex(slideIndex - 1);
    } else {
      setSlideIndex(config.slides.length - 1);
    }
  };
  const handleRightSlide = () => {
    if (slideIndex < config.slides.length - 1) {
      setSlideIndex(slideIndex + 1);
    } else {
      setSlideIndex(0);
    }
  };
  const handleChooseSlide = (index) => {
    setSlideIndex(index);
  };
  return (
    <div className={cx("wrapper")}>
      <div className={cx("left-arrow")} onClick={handleLeftSlide}>
        <FontAwesomeIcon icon={faArrowLeft} />
      </div>

      <Image src={config.slides[slideIndex]} alt="" className={cx("sliders")} />

      <div className={cx("right-arrow")} onClick={handleRightSlide}>
        <FontAwesomeIcon icon={faArrowRight} />
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

export default IntroSection;
