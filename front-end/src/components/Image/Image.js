import PropTypes from "prop-types";
import classNames from "classnames";
import styles from "./Image.module.scss";
import images from "~/assets/images";
import { useState } from "react";
function Image({
  src,
  alt,
  className,
  customFallBack = images.noImage,
  ...props
}) {
  const [fallBack, setfallBack] = useState("");
  const handleError = () => {
    setfallBack(customFallBack);
  };
  return (
    <img
      src={fallBack || src}
      alt={alt}
      className={classNames(styles.wrapper, className)}
      onError={handleError}
      {...props}
    />
  );
}
Image.propTypes = {
  src: PropTypes.string,
  alt: PropTypes.string,
  className: PropTypes.string,
  customFallBack: PropTypes.string,
};

export default Image;
