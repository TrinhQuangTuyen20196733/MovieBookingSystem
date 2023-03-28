import store from "~/components/Redux/store";
import { addAuthentication } from "~/components/Redux/action";
import { validateEmail, validatePassword } from "./validate";
const handleLogin = (emailLogin, passwordLogin) => {
  if (
    !validateEmail.test(emailLogin) ||
    !validatePassword.test(passwordLogin)
  ) {
    alert(
      "Email hoặc mật khẩu không chính xác!!! Lưu ý mật khẩu phải có ít nhất 8 kí tự,  1 chữ cái  và 1 số"
    );
    return;
  }

  const loginInfo = {
    email: emailLogin,
    password: passwordLogin,
  };
  fetch("http://localhost:8080/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(loginInfo),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(
          "Tài khoản hoặc mật khẩu của bạn không đúng, vui lòng nhập lại!"
        );
      }
      return response.json();
    })
    .then((data) => {
      console.log(data);
      let rolesAuthentication = [];
      data.userDTO.accountDTO.roles.map((role) => {
        return rolesAuthentication.push(role);
      });

      const authentication = {
        email: data.userDTO.accountDTO.email,
        password: data.userDTO.accountDTO.password,
        roles: rolesAuthentication,
        lastName: data.userDTO.lastName,
      };
      console.log(authentication);
      store.dispatch(addAuthentication(authentication));
      localStorage.setItem("jwtToken", data.accessToken);
    })
    .catch((error) => {
      alert(error.message);
    });
};
export default handleLogin;
