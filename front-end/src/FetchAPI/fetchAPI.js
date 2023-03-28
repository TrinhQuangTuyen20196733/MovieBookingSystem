const fetchAPI = (url, method, data = {}) => {
  const jwtToken = localStorage.getItem("jwtToken");
  return fetch(url, {
    method: method,
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
      Authorization: `Bearer ${jwtToken}`,
      charset: "UTF-8",
    },
    // body: JSON.stringify(data),
    ...(method.toUpperCase() === "GET" || method.toUpperCase() === "DELETE"
      ? {}
      : { body: JSON.stringify(data) }),
  });
};
export default fetchAPI;
