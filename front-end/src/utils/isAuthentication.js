import getAuthentication from "./getAuthentication";
const isAuthentication = () => {
  return !!getAuthentication().email;
};
export default isAuthentication;
