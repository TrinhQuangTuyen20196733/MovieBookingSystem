import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import classNames from "classnames/bind";
import images from "~/assets/images";
import Button from "~/components/Button";
import styles from "./Header.module.scss";
import Menu from "~/components/Menu";
import { faBars, faX } from "@fortawesome/free-solid-svg-icons";
import ActionItem from "~/components/ActionItem";
import Tippy from "@tippyjs/react/headless";
import PoperWrapper from "~/components/PoperWrapper";
import { Link } from "react-router-dom";
import config from "~/config";
import { useState } from "react";
import getAuthentication from "~/utils/getAuthentication";
import isAuthentication from "~/utils/isAuthentication";
import { useDispatch } from "react-redux";
import { removeAuthentication } from "~/components/Redux/action";
import handleLogin from "~/utils/handleLogin";
const cx = classNames.bind(styles);
function Header() {
  const authenticationState = isAuthentication();
  const authentication = getAuthentication();
  const [emailLogin, setEmailLogin] = useState("");
  const [passwordLogin, setPasswordLogin] = useState("");
  const [actionVisible, setActionVisible] = useState(false);
  const dispatch = useDispatch();

  const [showMenu, setShowMenu] = useState(false);

  const handleClickMenu = () => {
    setShowMenu(!showMenu);

    console.log("Da click menu");
  };
  const handleAction = () => {
    localStorage.removeItem("jwtToken");
    dispatch(removeAuthentication());
  };

  return (
    <div className={cx("wrapper")}>
      <Tippy
        interactive
        trigger="click"
        placement="bottom"
        render={(attrs) => (
          <PoperWrapper className={cx("menu-list")}>
            {authentication.roles.includes("ADMIN") ? (
              <>
                <Link to={config.routes.MovieList} className={cx("menu-link")}>
                  Danh sách phim
                </Link>
                <Link to={config.routes.UserList} className={cx("menu-link")}>
                  Danh sách người dùng
                </Link>

                <Link className={cx("menu-link")}>
                  Danh sách phòng chiếu phim
                </Link>
                <Link
                  to={config.routes.SessionList}
                  className={cx("menu-link")}
                >
                  Danh sách lịch chiếu phim
                </Link>
                <Link
                  to={config.routes.ServiceList}
                  className={cx("menu-link")}
                >
                  Đồ ăn và nước uống
                </Link>
                <Link className={cx("menu-link")}>Danh sách hóa đơn</Link>
              </>
            ) : (
              <>
                <Link
                  to={config.routes.FilmSchedule}
                  className={cx("menu-link")}
                >
                  lịch chiếu
                </Link>
                <Link
                  to={config.routes.TheaterSystem}
                  className={cx("menu-link")}
                >
                  hệ thống rạp
                </Link>
                <Link to={config.routes.Event} className={cx("menu-link")}>
                  khuyến mãi | sự kiện
                </Link>
                <Link to={config.routes.Advertise} className={cx("menu-link")}>
                  dịch vụ quảng cáo
                </Link>
                <Link
                  to={config.routes.FilmSchedule}
                  className={cx("menu-link")}
                >
                  tuyển dụng
                </Link>
                <Link
                  to={config.routes.Information}
                  className={cx("menu-link")}
                >
                  về chúng tôi
                </Link>
              </>
            )}
          </PoperWrapper>
        )}
      >
        {!showMenu ? (
          <Menu title="MENU" onClick={handleClickMenu}>
            <FontAwesomeIcon icon={faBars} />
          </Menu>
        ) : (
          <Menu title="CLOSE" onClick={handleClickMenu}>
            <FontAwesomeIcon icon={faX} />
          </Menu>
        )}
      </Tippy>

      <Button className={cx("ticket")} primaryColor text>
        MUA VÉ
      </Button>
      <img alt="" src={images.logo} className={cx("logo")} />
      <div className={cx("action")}>
        {!authenticationState ? (
          <>
            <ActionItem
              src={images.iconInstagram}
              alt="IconInstagram"
              href="https://www.instagram.com/bhdstar.cineplex/"
            />
            <ActionItem
              src={images.iconTiktok}
              alt="IconTiktok"
              href="https://www.tiktok.com/@bhdstar.cineplex"
            />

            <ActionItem
              src={images.iconYoutube}
              alt="IconYoutube"
              href="https://www.youtube.com/user/BHDStar"
            />
            <ActionItem
              src={images.iconFacebook}
              alt="IconFacebook"
              href="https://www.facebook.com/BHDStar"
            />
            <Tippy
              visible={actionVisible}
              interactive
              placement="bottom-start"
              render={(attrs) => (
                <PoperWrapper className={cx("login-info")}>
                  <input
                    placeholder="Email"
                    className={cx("login-input")}
                    value={emailLogin}
                    onChange={(e) => {
                      setEmailLogin(e.target.value);
                    }}
                  />
                  <input
                    placeholder="Password"
                    className={cx("login-input")}
                    value={passwordLogin}
                    type="password"
                    onChange={(e) => {
                      setPasswordLogin(e.target.value);
                    }}
                  />
                  <div className={cx("login-action")}>
                    <Link to={config.routes.Home}>
                      <Button
                        primaryColor
                        text
                        loginButton
                        className={cx("btn-login")}
                        onClick={() => {
                          handleLogin(emailLogin, passwordLogin);
                          setActionVisible(false);
                        }}
                      >
                        ĐĂNG NHẬP
                      </Button>
                    </Link>

                    <p className={cx("password")}>Quên mật khẩu</p>
                  </div>

                  <Button
                    text
                    primaryColor
                    className={cx("membership")}
                    to="/dang-ki-tai-khoan"
                    onClick={() => setActionVisible(false)}
                  >
                    Đăng kí thành viên
                  </Button>
                </PoperWrapper>
              )}
            >
              <Button
                text
                primaryColor
                className={cx("btn-login")}
                onClick={() => setActionVisible(!actionVisible)}
              >
                ĐĂNG NHẬP
              </Button>
            </Tippy>
          </>
        ) : (
          <div className={cx("log-out")}>
            <span className={cx("user-name")}>{authentication.lastName} |</span>
            <Link
              style={{ textDecoration: "none", color: "none" }}
              to={config.routes.Home}
              className={cx("btn-logout")}
              onClick={handleAction}
            >
              THOÁT
            </Link>
          </div>
        )}
      </div>
      <img alt="" src={images.lineHeader1} className={cx("line-header1")} />
    </div>
  );
}

export default Header;
