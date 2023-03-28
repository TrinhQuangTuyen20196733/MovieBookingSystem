import classNames from "classnames/bind";
import styles from "./ChooseSlide.module.scss";
import PropTypes from "prop-types";
const cx = classNames.bind(styles);
function ChooseSlide({ onClick, selected }) {
  const classes = cx("wrapper", {
    selected,
  });
  return <div className={classes} onClick={onClick}></div>;
}
ChooseSlide.propTypes = {
  onClick: PropTypes.func,
  selected: PropTypes.bool,
};

export default ChooseSlide;
