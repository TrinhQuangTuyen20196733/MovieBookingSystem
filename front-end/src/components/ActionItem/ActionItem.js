import classNames from "classnames/bind";
import styles from "./ActionItem.scss";
const cx = classNames.bind(styles);
function ActionItem({ src, href, alt }) {
  return (
    <a className={cx("action-link")} href={href}>
      <img className={cx("action-icon")} alt={alt} src={src} />
    </a>
  );
}

export default ActionItem;
