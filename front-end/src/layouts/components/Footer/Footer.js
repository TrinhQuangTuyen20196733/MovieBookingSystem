import classNames from "classnames/bind";
import styles from "./Footer.module.scss";
import images from "~/assets/images";
import Image from "~/components/Image/Image";
import { Link } from "react-router-dom";
import config from "~/config";
import ScrollTo from "~/components/ScrollTo/ScrollTo";
const cx = classNames.bind(styles);
function Footer() {
  return (
    <div className={cx("wrapper")}>
      <div className={cx("logo")}>
        <img src={images.logoFooter} alt="BHD Star" />
      </div>
      <div className={cx("container")}>
        <div className={cx("about-me")}>
          <h3 className={cx("title")}>VỀ BHD STAR</h3>
          <div className={cx("menu-container")}>
            <ul className={cx("menu")}>
              <Link
                to={config.routes.TheaterSystem}
                className={cx("menu-item")}
              >
                Hệ thống rạp
              </Link>
              <Link to={config.routes.Recruitment} className={cx("menu-item")}>
                Tuyển dụng
              </Link>
              <Link to={config.routes.Information} className={cx("menu-item")}>
                Liên hệ
              </Link>
              <a href="http://online.gov.vn/Home/WebDetails/46605">
                <Image
                  src={images.dathongbao}
                  alt="Đã thông báo - Bộ Công Thương"
                  with="150px"
                  height="60px"
                />
              </a>
            </ul>
          </div>
        </div>
        <div className="rule-and-contract">
          <h3 className={cx("title")}>QUY ĐỊNH & ĐIỀU KHOẢN</h3>
          <div className={cx("menu-container")}>
            <ul className={cx("menu")}>
              <Link to={config.routes.MemberRule} className={cx("menu-item")}>
                Quy định thành viên
              </Link>
              <Link to={config.routes.Rules} className={cx("menu-item")}>
                Điều khoản
              </Link>
              <Link
                to={config.routes.TicketInstruction}
                className={cx("menu-item")}
              >
                Hướng dẫn đặt vé trực tuyến
              </Link>
              <Link to={config.routes.Policy} className={cx("menu-item")}>
                Quy định và chính sách chung
              </Link>
              <Link to={config.routes.UserSecurity} className={cx("menu-item")}>
                Chính sách bảo vệ thông tin cá nhân của người tiêu dùng
              </Link>
            </ul>
          </div>
        </div>
      </div>
      <div className={cx("copyright")}>© 2015 BHD Star Cineplex</div>
      <div className={cx("to-top")}>
        <span className={cx("title-to-top")}>Lên đầu trang</span>
        <ScrollTo primaryColor round className={cx("up-to-top")}>
          ↑
        </ScrollTo>
      </div>
    </div>
  );
}

export default Footer;
