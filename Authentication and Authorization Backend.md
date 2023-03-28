# BHDStar
Để xác thực và phân quyền ở phía back-end, ta sẽ dùng Spring Security và JWT (JSon Web Token).
JWT (JSON Web Token) là một chuẩn mã hóa dữ liệu dưới dạng JSON được sử dụng để xác thực và phân quyền truy cập cho các ứng dụng web.
Cấu trúc của JWT gồm có 3 phần:
+ Phần Header gồm  thông tin về kiểu mã hóa và thuật toán mã hóa được sử dụng
//////////
{
  "alg": "HS256",
  "typ": "JWT"
}
/////////////
+ Phần  Payload : (hay ở đây còn được gọi là các claims) chứa thông tin người dùng để thực hiện xác thực và phân quyền.
+ Phần Signature: là một chuỗi được mã hóa bởi header, payload, một khóa bí mật bằng một thuật toán nhất định.
Do bản thên của signature đã bao gồm cả header, payload nên nó có thể được sử dụng để xác định tính toàn viện của dữ liệu khi truyền tải.
//////////////////Hàm tạo JWTToken để gửi trả về front-end"//////////
public String generateToken(AccountDetails accountDetails){
        Date now =new Date();
        Date expiratedDate = new Date(now.getTime()+SecurityConstant.EXPIRATION_TIME);
        // create json web token from account ID - this is JWT signature
        return Jwts.builder()
                .setSubject(Integer.toString(accountDetails.getAccount().getId()))
                .setIssuedAt(now)
                .setExpiration(expiratedDate)
                .signWith(Keys.hmacShaKeyFor(SecurityConstant.SECRET_KEY.getBytes()),SignatureAlgorithm.HS512)
                .compact()
                //////////////////////////////////////
                Ở đây, ta đã tạo ra chuỗi JWTToken với subject(tiêu đề) chính là account_id, và thêm các trường phát hành và thời gian sử dụng vào claims để mã hóa,
                thuật toán đước dử dụng là SignatureAlgorithm.HS512 với khóa bí mật SecurityConstant.SECRET_KEY.
  /////////////// Hàm trích xuất account_id khi có chuỗi JWT token ///////////////////////
   public int getAccountIdFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()// create parserBuilder object  to decoder
                .setSigningKey(SecurityConstant.SECRET_KEY.getBytes())//set Signing Key cho parseBuilder object
                .build()  // create JWT Parser to decode token
                .parseClaimsJws(token) // decode token and return JWS - Json web signature
                .getBody();// return Claims object contains information about authentication of JWT payload
        return Integer.parseInt(claims.getSubject());// return Subject of claims/ AccountID
    }
    /////////////////////////////////////////////////////////
                 Ở đây, ta tiến hành set khóa bí mật cho đối tượng parserBuilder object và thực hiện quá trình giải mã
                 Sau khi giải mã ta sẽ thu được jwt signature và ta lấy phần body của jwt sau đó lấy subject của claims đó thì ta thu được account_id.
* Luồng hoạt động:
Ở đây, với mỗi request được gửi lên server, ta sẽ cho nó đi qua một bộ lọc JWTAuthenticationFilter .
Tại đây, nó sẽ kiểm tra trong phần Authentication của Header xem có thông tin không? Nếu có thông tin thì sẽ trích xuất ra chuỗi JWT Token để thực hiện xác thực
và phân quyền
+ Trường Authentication của Header là trường để lưu các thông tin xác thực. Khi có chuỗi JWT gửi về từ server, bên Front-end sẽ tự phải thêm một chuỗi vào trường này cho 
mọi request gửi lên server. Nó là một chuỗi gồm "tenloaitoken jwttoken".
Ở đây, vì ta dùng chuẩn JWT nên tenloaitoken sẽ là Bearer .
Tại JWTAuthenticationFilter để lấy được jwtToken, nó chỉ việc loại bỏ đi "Bearer " của phần Authen. 
Ở đây, xảy ra 2 trường hợp
+ Nếu tồn tại chuỗi JWT Token và chuỗi đó hợp lệ thì chúng ta sẽ trích xuất account_id từ JWT Token và từ đó tìm là được account của người dùng.
Nếu account tồn tại ta sẽ lưu nó vào SpringContext để thực hiện quá trình xác thực.
+ Nếu chuỗi JWT chưa tồn tại thì sẽ chuyển đến bộ lọc tiếp theo và đây là lần đăng nhập đầu tiên.
//////////////////////Login Controller  //////////////
  @PostMapping("/login")

    public LoginResponse authenticateAccount(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        AccountEntity account = accountDetails.getAccount();
        // Trả về jwt cho người dùng.
        String jwtToken = jwtService.generateToken(accountDetails);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccount(account);
        loginResponse.setAccessToken(jwtToken);

        return loginResponse;
    }
    /////////////////////////////////////////////////////////////////////// 
    Từ thông tin mà người dùng gửi lên, authenticationManager sẽ có thông tin để xác thực tài khoản. Nếu thông tin thành công, thì sẽ lưu thông tin vào Spring Context
    và tạo ra chuỗi JWT Token gửi trả về cho client.
    
    *********** Cơ chế xác thực của Spring Security ***********************
    Trước tiên, trong SpringContextHolder ta sẽ lưu một đối tượng authentication (UsernamePasswordAuthenticationToken object).
    Đối tượng này lưu thông tin người dùng dưới đạn một object là UserDetails  để xác thực và các roles của người dùng.
    Để thực hiện xác thực trong Spring Security, đối tượng authenticationManager là đối tượng trực tiếp để quản lý việc xác thực
    Trước tiên, nó sẽ gọi hàm authenticate() và truyền vào đối tượng authentication ta lưu trong SpringContext.
    Tiếp đến nó gọi hàm loadUserByUserName mà ta implement từ interface UserDetailsService. Hàm này sẽ nhận vào tham số chính là username của đối tượng UserDetails 
    trong authentication và từ đó ta sẽ tìm ra được account tương ứng.
    Sau đó, authenticationManager sẽ đối chiếu đối tượng vừa tìm được với đối tượng trong authentication
    + Nếu kết quả khớp, trả về đối tượng authentication thành công
    + Thất bại,sẽ ném ra một ngoại lệ.
    
    
    
    
    
    
    
    
    
    
    
    
    
    
