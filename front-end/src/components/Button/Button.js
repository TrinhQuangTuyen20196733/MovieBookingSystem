import PropTypes from "prop-types";
import classNames from "classnames/bind";
import styles from "./Button.module.scss";
import { Link } from "react-router-dom";
import { forwardRef } from "react";
const cx = classNames.bind(styles);
const Button = forwardRef(
  (
    {
      children,
      href,
      to,
      text = false,
      primary = false,
      primaryColor = false,
      loginButton = false,
      isTicketButton = false,
      isMemberRegister = false,
      className,
      onClick,
      ...passProps
    },
    ref
  ) => {
    let Comp = "button";
    const props = {
      onClick,
      ...passProps,
    };
    if (to) {
      props.to = to;
      Comp = Link;
    } else if (href) {
      props.href = href;
      Comp = "a";
    }
    const classes = cx("wrapper", {
      [className]: className,
      primary,
      text,
      primaryColor,
      loginButton,
      isTicketButton,
      isMemberRegister,
    });

    return (
      <Comp className={classes} {...props} ref={ref}>
        <span className={cx("title")}>{children}</span>
      </Comp>
    );
  }
);
Button.propTypes = {
  children: PropTypes.node.isRequired,
  href: PropTypes.bool,
  to: PropTypes.string,
  text: PropTypes.bool,
  light: PropTypes.bool,
  primary: PropTypes.bool,
  primaryColor: PropTypes.bool,
  className: PropTypes.string,
  onClick: PropTypes.func,
  isTicketButton: PropTypes.bool,
  isMemberRegister: PropTypes.bool,
};
export default Button;
