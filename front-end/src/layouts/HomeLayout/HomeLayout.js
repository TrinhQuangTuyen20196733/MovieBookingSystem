import Header from "../components/Header";
import Footer from "../components/Footer";
import styles from "./HomeLayout.module.scss";
import PropTypes from "prop-types";
import classNames from "classnames/bind";

const cx = classNames.bind(styles);
function HomeLayout({ children }) {
 
  return (
    <div className={cx("wrapper")}>
      <Header />
      {children}
      <Footer />
    </div>
  );
}
HomeLayout.propTypes = {
  children: PropTypes.node.isRequired,
};

export default HomeLayout;
