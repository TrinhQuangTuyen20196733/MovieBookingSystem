export const addAuthentication = (account) => {
  return {
    type: "saveAuthentication",
    payload: account,
  };
};
export const removeAuthentication = () => {
  return {
    type: "removeAuthentication",
  };
};
