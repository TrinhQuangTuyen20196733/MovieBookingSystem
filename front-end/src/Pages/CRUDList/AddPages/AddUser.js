import styles from "./AddPage.module.scss";
import classNames from "classnames/bind";
import Input from "~/components/Input/Input";
import { useEffect, useState } from "react";
import Button from "~/components/Button";
import {
  validateEmpty,
  validateEmail,
  validatePhoneNumber,
  validatePassword,
} from "~/utils/validate";
import fetchAPI from "~/FetchAPI/fetchAPI";
import { useLocation } from "react-router-dom";
import moment from "moment";
import config from "~/config";
const cx = classNames.bind(styles);
function AddUser() {
  const location = useLocation();
  const user_id = location.state?.user_id;
  const [lastName, setLastName] = useState("");
  const [firstName, setFirstName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [birthDay, setBirthDay] = useState();
  const [address, setAddress] = useState("");
  const [roles, setRoles] = useState();
  const [checkUser, setCheckUser] = useState(false);
  const [checkAdmin, setCheckAdmin] = useState(false);
  const [checkManager, setCheckManager] = useState(false);
  const [id, setId] = useState(0);
  const [accountId, setAccountId] = useState(0);
  useEffect(() => {
    if (user_id) {
      fetchAPI(`http://localhost:8080/admin/users/${user_id}`, "GET")
        .then((response) => {
          if (response.status === 401) {
            throw new Error("Bạn không có quyền truy cập đường dẫn này");
          } else if (response.status === 404) {
            throw new Error("Người dùng này không tồn tại");
          }

          return response.json();
        })
        .then((data) => {
          console.log(data);
          setId(data.id);
          setAccountId(data.accountDTO.id);
          setLastName(data.lastName);
          setFirstName(data.firstName);
          setEmail(data.accountDTO.email);
          setPhoneNumber(data.phoneNumber);
          setAddress(data.address);
          const updatebirthDay = moment(data.birthDay).format("YYYY-MM-DD");
          setBirthDay(updatebirthDay);
          const updateRoles = {
            USER: data.accountDTO.roles.includes("USER") ?? false,
            ADMIN: data.accountDTO.roles.includes("ADMIN") ?? false,
            MANAGER: data.accountDTO.roles.includes("MANAGER") ?? false,
          };
          setRoles(updateRoles);
          setCheckUser(updateRoles.USER);
          setCheckAdmin(updateRoles.ADMIN);
          setCheckManager(updateRoles.MANAGER);
        })
        .catch((error) => {
          alert(error.message);
        });
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // const [role, setRole] = useState("");

  const handleSubmit = () => {
    const roleKey = Object.keys(roles);
    const roleList = roleKey.filter((role) => roles[role] === true);

    if (!validateEmail.test(email) || !validatePassword.test(password)) {
      alert(
        "Email hoặc mật khẩu không chính xác!!! Lưu ý mật khẩu phải có ít nhất 8 kí tự,  1 chữ cái  và 1 số"
      );
      return;
    } else if (password !== passwordConfirm) {
      alert("Mật khẩu không khớp. Vui lòng nhập lại");
      return;
    } else if (!validatePhoneNumber.test(phoneNumber)) {
      alert("Số điện thoại không đúng, vui lòng nhập lại!");
      return;
    } else if (!validateEmpty(firstName, lastName, address, birthDay)) {
      alert("Vui lòng nhập đầy đủ thông tin!");
      return;
    } else if (!roleList.length) {
      alert("Vui lòng nhập quyền của người dùng!");
      return;
    }
    const userInfor = {
      id,
      lastName,
      firstName,
      phoneNumber,
      birthDay,
      address,
      accountDTO: {
        id: accountId,
        email,
        password,
        roles: roleList,
      },
    };
    console.log(userInfor);
    if (!user_id) {
      fetchAPI("http://localhost:8080/admin/users", "POST", userInfor)
        .then((response) => {
          if (response.status === 401) {
            throw new Error("Bạn không có quyền truy cập đường dẫn này");
          }
          if (response.status === 200) {
            alert("Bạn đã thêm người dùng thành công");
          }

          return response.json();
        })
        .then((data) => {
          if (data.description) {
            alert(data.description);
          }
        })
        .catch((error) => {
          alert(error.message);
        });
    } else {
      fetchAPI(`http://localhost:8080/admin/users/${id}`, "PUT", userInfor)
        .then((response) => {
          if (response.status === 401) {
            throw new Error("Bạn không có quyền truy cập đường dẫn này");
          }
          if (response.status === 200) {
            alert("Bạn đã cập nhật người dùng thành công");
          }
          return response.json();
        })
        .then((data) => {
          console.log(data);
        })
        .catch((error) => {
          alert(error.message);
        });
    }
  };
  return (
    <div className={cx("wrapper")}>
      <h1 className={cx("title")}>Thông tin người dùng</h1>
      <div className={cx("container")}>
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
          defaultValue={birthDay}
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
        <Input
          label="Email (*)"
          type="email"
          value={email}
          onChange={(e) => {
            setEmail(e.target.value);
          }}
        />
        <Input
          label="Mật khẩu (*)"
          type="password"
          value={password}
          onChange={(e) => {
            setPassword(e.target.value);
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
        <div className={cx("role")}>
          <span className={cx("role-title")}>Vai trò</span>
          <label>
            <input
              type="checkbox"
              name="USER"
              onChange={() => {
                setRoles({
                  ...roles,
                  USER: !checkUser,
                });
                setCheckUser(!checkUser);
              }}
              checked={checkUser}
            />
            USER
          </label>
          <label>
            <input
              type="checkbox"
              name="ADMIN"
              onChange={() => {
                setRoles({
                  ...roles,
                  ADMIN: !checkAdmin,
                });
                setCheckAdmin(!checkAdmin);
              }}
              checked={checkAdmin}
            />
            ADMIN
          </label>
          <label>
            <input
              type="checkbox"
              name="MANGANER"
              onChange={() => {
                setRoles({
                  ...roles,
                  MANAGER: !checkManager,
                });
                setCheckManager(!checkManager);
              }}
              checked={checkManager}
            />
            MANAGER
          </label>
        </div>
        <div className={cx("button")}>
          <Button
            text
            primaryColor
            to={config.routes.UserList}
            className={cx("btn-back")}
          >
            Xem danh sách
          </Button>
          <Button
            text
            primaryColor
            className={cx("btn-submit")}
            onClick={handleSubmit}
          >
            Submit
          </Button>
        </div>
      </div>
    </div>
  );
}

export default AddUser;
