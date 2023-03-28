import { Navigate } from "react-router-dom";
import getAuthentication from "~/utils/getAuthentication";
import isAuthentication from "~/utils/isAuthentication";
import React from "react";
import config from "~/config";

function PrivateRoute({ component: Component, layout: Layout, role, ...rest }) {
  const authenticationState = isAuthentication();
  const isAccess = getAuthentication().roles.includes(role);
  return (
    <>
      {" "}
      {!authenticationState ? (
        <Navigate to={config.routes.Register} />
      ) : isAccess ? (
        <Layout>
          <Component />
        </Layout>
      ) : (
        <Navigate to={config.routes.UnAuthorization} />
      )}
    </>
  );
}

export default PrivateRoute;
