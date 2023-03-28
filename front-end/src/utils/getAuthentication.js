import { useSelector } from "react-redux";
import { searchAuthentication } from "~/components/Redux/selector";
const getAuthentication = () => {
  // eslint-disable-next-line react-hooks/rules-of-hooks
  return useSelector(searchAuthentication);
};
export default getAuthentication;
