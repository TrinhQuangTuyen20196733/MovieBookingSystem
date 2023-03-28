const initState = {
  authentication: {
    email: "",
    password: "",
    roles: [],
  },
};

const rootReducer = (state = initState, aciton) => {
  switch (aciton.type) {
    case "saveAuthentication":
      return {
        ...state,
        authentication: aciton.payload,
      };
    case "removeAuthentication":
      return {
        ...state,
        authentication: {
          email: "",
          password: "",
          roles: [],
        },
      };
    default:
      return state;
  }
};
export default rootReducer;
