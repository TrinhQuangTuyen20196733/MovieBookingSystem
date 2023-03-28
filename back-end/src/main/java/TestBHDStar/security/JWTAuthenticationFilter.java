package TestBHDStar.security;

import TestBHDStar.Service.Impl.AccountDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JWTService jwtService;
    @Autowired
    AccountDetailsService accountDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
 try {
     // Get jwt from request
     String jwt = getJwtFromRequest(request);
     if (StringUtils.hasText(jwt) && jwtService.isValidToken(jwt)){
         int account_id = jwtService.getAccountIdFromJWT(jwt);
         // Lấy ra đối tượng accountDetails để set thông tin cho Spring Security Context
          UserDetails accountDetails = accountDetailsService.loadUserById(account_id);
         if (accountDetails!=null) {
             //Nếu account tồn tại, set thông tin cho Security Context
             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(accountDetails,null,accountDetails.getAuthorities());
             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(authentication);
         }
     }
 } catch (Exception ex) {
     log.error("Failed to set user authentication",ex);
 }
        filterChain.doFilter(request, response);
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        // Authorization of header chứa các thông tin về xác thực và phân quyền người dùng
        String bearerToken = request.getHeader("Authorization");
        //Kiểm tra xem header Authorization có chứa thông tin jwt không. Theo chuẩn JWT thì Authorization Header chứa tên loại token và giá trị jwt
        // Đây là chuẩn JWT => authorization header = Bearer [JWTvalue]
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
