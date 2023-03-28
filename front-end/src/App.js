import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PrivateRoute from "./routes/PrivateRoute";

import publicRoutes from "./routes/routes";
import { privateRoutes } from "./routes/routes";
function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          {publicRoutes.map((route, index) => {
            const Page = route.component;
            const Layout = route.layout;
            return (
              <Route
                key={index}
                path={route.path}
                element={
                  <Layout>
                    <Page />
                  </Layout>
                }
              />
            );
          })}
          {privateRoutes.map((route, index) => {
            const Page = route.component;
            const Layout = route.layout;
            const role = route.role;
            return (
              <Route
                path={route.path}
                key={index}
                element={
                  <PrivateRoute component={Page} layout={Layout} role={role} />
                }
              />
            );
          })}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
