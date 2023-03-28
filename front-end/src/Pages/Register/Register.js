import classNames from "classnames/bind";
import styles from "./Register.module.scss";
import Input from "~/components/Input";
import EventSection from "~/layouts/components/EventSection";
import Button from "~/components/Button";
import { Link } from "react-router-dom";
import { useState } from "react";

import fetchAPI from "~/FetchAPI/fetchAPI";
import handleLogin from "~/utils/handleLogin";
import {
  validateEmpty,
  validateEmail,
  validatePassword,
  validatePhoneNumber,
} from "~/utils/validate";
const cx = classNames.bind(styles);
function Register() {
  const [emailLogin, setEmailLogin] = useState("");
  const [passwordLogin, setPasswordLogin] = useState("");
  // const [role, setRole] = useState("");
  const [lastName, setLastName] = useState("");
  const [firstName, setFirstName] = useState("");
  const [emailSignUp, setEmailSignUp] = useState("");
  const [passwordSignUp, setPasswordSignUp] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [birthDay, setBirthDay] = useState("");
  const [address, setAddress] = useState("");
  const [checked, setChecked] = useState(false);

  const handleSignUp = () => {
    if (
      !validateEmail.test(emailSignUp) ||
      !validatePassword.test(passwordSignUp)
    ) {
      alert(
        "Email hoặc mật khẩu không chính xác!!! Lưu ý mật khẩu phải có ít nhất 8 kí tự,  1 chữ cái  và 1 số"
      );
      return;
    } else if (passwordSignUp !== passwordConfirm) {
      alert("Mật khẩu không khớp. Vui lòng nhập lại");
      return;
    } else if (!validatePhoneNumber.test(phoneNumber)) {
      alert("Số điện thoại không đúng, vui lòng nhập lại!");
      return;
    } else if (!validateEmpty(firstName, lastName, address, birthDay)) {
      alert("Vui lòng nhập đầy đủ thông tin!");
      console.log(111111, birthDay);
      return;
    } else if (!checked) {
      alert("Vui lòng đồng ý các điều khoản");
      return;
    }
    const signUpInfo = {
      lastName,
      firstName,
      phoneNumber,
      address,
      birthDay,
      accountDTO: {
        email: emailSignUp,
        password: passwordSignUp,
        roles: ["USER"],
      },
    };
    console.log(signUpInfo);
    fetchAPI("http://localhost:8080/admin/users", "POST", signUpInfo)
      .then((response) => {
        if (response.status === 401) {
          throw new Error("Bạn không có quyền truy cập đường dẫn này");
        }
        return response.json();
      })
      .then((data) => {})
      .catch((error) => {
        alert(error.message);
      });
  };

  return (
    <>
      <div className={cx("wrapper")}>
        <div className={cx("title")}>
          <h3 className={cx("product-title")}>THÀNH VIÊN</h3>
          <span className={cx("separator-character")}>|</span>
          <h3 className={cx("product-title")}>FAQ</h3>
          <span className={cx("separator-character")}>|</span>
          <h3 className={cx("product-title")}>QUY ĐỊNH</h3>
        </div>
        <div className={cx("container")}>
          <div className={cx("login")}>
            <h4>ĐĂNG NHẬP</h4>
            <div className={cx("login-info")}>
              <Input
                label="Email (*)"
                type="email"
                value={emailLogin}
                onChange={(e) => {
                  setEmailLogin(e.target.value);
                }}
              />
              <Input
                label="Mật khẩu (*)"
                type="password"
                value={passwordLogin}
                onChange={(e) => {
                  setPasswordLogin(e.target.value);
                }}
              />

              <div className={cx("login-action")}>
                <Button
                  text
                  primaryColor
                  className={cx("btn-login")}
                  onClick={() => {
                    handleLogin(emailLogin, passwordLogin);
                  }}
                >
                  ĐĂNG NHẬP
                </Button>
                <Link to="/quen-mat-khau" className={cx("password-forgot")}>
                  Quên mật khẩu
                </Link>
              </div>
            </div>
          </div>
          <div className={cx("sign-up")}>
            <h4>ĐĂNG KÍ MỚI</h4>
            <div className={cx("sign-up-info")}>
              <Input
                label="Họ (*)"
                value={lastName}
                onChange={(e) => {
                  setLastName(e.target.value);
                }}
              />
              <Input
                label="Tên (*)"
                value={firstName}
                onChange={(e) => {
                  setFirstName(e.target.value);
                }}
              />
              <Input
                label="Email (*)"
                type="email"
                value={emailSignUp}
                onChange={(e) => {
                  setEmailSignUp(e.target.value);
                }}
              />
              <Input
                label="Mật khẩu (*)"
                type="password"
                value={passwordSignUp}
                onChange={(e) => {
                  setPasswordSignUp(e.target.value);
                }}
              />
              <Input
                label="Nhập lại mật khẩu (*)"
                type="password"
                value={passwordConfirm}
                onChange={(e) => {
                  setPasswordConfirm(e.target.value);
                }}
              />
              <Input
                label="Số điện thoại (*)"
                type="tell"
                value={phoneNumber}
                onChange={(e) => {
                  setPhoneNumber(e.target.value);
                }}
              />
              <Input
                label="Ngày sinh (*)"
                type="date"
                value={birthDay}
                onChange={(e) => {
                  setBirthDay(e.target.value);
                }}
              />
              <Input
                label="Địa chỉ liên hệ (*)"
                value={address}
                onChange={(e) => {
                  setAddress(e.target.value);
                }}
              />
              <label className={cx("policy-check")}>
                <input
                  type="checkbox"
                  name="dieu-khoan"
                  className={cx("policy-checkbox")}
                  onChange={(e) => setChecked(e.target.checked)}
                  checked={checked}
                />
                <span>Tôi đã đọc, hiểu và đồng ý với các điều khoản</span>
              </label>
              <Button
                text
                primaryColor
                className={cx("btn-login")}
                onClick={handleSignUp}
              >
                ĐĂNG KÝ
              </Button>
            </div>
          </div>
        </div>
      </div>
      <EventSection />
    </>
  );
}

export default Register;
