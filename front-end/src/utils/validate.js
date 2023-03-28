export const validateEmpty = (...props) => {
  return (
    props.filter((prop) => {
      return prop.trim() !== "";
    }).length === props.length
  );
};
export const validateEmail = new RegExp(
  "^[a-zA-Z0-9._:$!%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]$"
);
export const validatePassword = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
export const validatePhoneNumber = /(84|0[3|5|7|8|9])+([0-9]{8})\b/g;
