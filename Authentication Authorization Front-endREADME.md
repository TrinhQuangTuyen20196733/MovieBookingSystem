# BHDStar
Ở phía server, back-end đã tạo ra Jwt token và thông tin xác thực để gửi về trong lần đăng nhập và xử lý xác thực và phân quyền tại đó.
Tại client, chúng ta cần phải lưu thông tin xác thực này để xử lý xác thực và phân quyền với React-Router. Đồng thời , ta cũng sẽ lưu chuỗi JWT Token và sẽ gửi kèm nó 
lên trong trường Authentication của phần Header đối với mọi request.
* Để gửi kèm Jwt token lên, trước tiên ta sẽ lưu lại chuỗi jwt này vào localstorage, và sau đó thêm nó vào authentication của Header với mỗi làn gửi Request
//////////////////////////////////////
const fetchAPI = (url, method, data) => {
  const jwtToken = localStorage.getItem("jwtToken");
  return fetch(url, {
    method: method,
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
      Authorization: `Bearer ${jwtToken}`,
    },
    body: JSON.stringify(data),
  });
};
export default fetchAPI;
/////////////////////
 Authorization: `Bearer ${jwtToken}`,ở đây phần authentication sẽ được lưu thêm tên loại token mà ta sử dụng. Theo chuẩn JWT thì sẽ là "Bearer "
 ******************************
 Đối với việc xác thực và phân quyền react-router, ta sẽ phải lưu thông tin tài khoản mà server gửi về trong lần đăng nhập vào Redux Store.
 Và để khi Reload lại trang, không bị mất Redux Store ta sẽ phải sử dụng Redux-persist để lưu store xuống localStorage và trước khi lưu thì ta sẽ cần phải mã hóa để
 tránh bị đánh cắp thông tin
 ///////////////////////
 const persistConfig = {
  key: "root",
  storage: storage,
  transforms: [
    encryptTransform({
      secretKey: "my-secret-key",
      onError: function (error) {
        console.log(error);
      },
    }),
  ],
};
const persistedReducer = persistReducer(persistConfig, rootReducer);
const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
});
///////////////////////////////////////////
Ở đây, ta lưu xuống localStorage với tên của kho là root, trước khi lưu, ta mã hóa nó bằng 1 khóa bị mật ( "my-secret-key"")
Tiếp theo, để xác thực và phân quyền bằng React-Router, ta sẽ tạo ra một  PrivateRoute
//////////////////////
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
}//////////////////////
Ở đây, privateRoute về bản chất thì cũng như là một Route bình thường, chỉ khác ta sẽ validate thông tin authentication để phân quyền cho route đó.
Với trường hợp chưa đăng nhập thì redirect tới trang đăng nhập.
Nếu đăng nhập mà không có quyền thì chuyển tới thông báo chưa phân quyền
Còn nếu đã đang nhập và có đúng quyền thì sẽ tới được trang đích.
