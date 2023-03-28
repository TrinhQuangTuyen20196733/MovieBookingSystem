import classNames from "classnames/bind";
import PropTypes from "prop-types";
import styles from "./ScrollTo.module.scss";
const cx = classNames.bind(styles);
function ScrollTo({
  round = false,
  primaryColor = false,
  DomElement,
  children,
  className,
}) {
  const handleScrollTo = (event) => {
    event.preventDefault();
    if (DomElement) {
      DomElement.scrollIntoView({ behavior: "smooth" });
    } else {
      window.scrollTo({
        top: 0,
        left: 0,
        behavior: "smooth",
      });
    }
  };
  const classes = cx("wrapper", {
    [className]: className,
    primaryColor,
    round,
  });
  return (
    <div className={classes} onClick={handleScrollTo}>
      {children}
    </div>
  );
}
ScrollTo.propTypes = {
  round: PropTypes.bool,
  primaryColor: PropTypes.bool,
  children: PropTypes.node,
  className: PropTypes.string,
};
export default ScrollTo;
