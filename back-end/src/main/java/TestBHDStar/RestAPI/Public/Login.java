package TestBHDStar.RestAPI.Public;

import TestBHDStar.security.AccountDetails;
import TestBHDStar.DTO.LoginRequest;
import TestBHDStar.DTO.LoginResponse;
import TestBHDStar.entity.AccountEntity;
import TestBHDStar.mapper.mapperImpl.UserMapper;
import TestBHDStar.security.JWTService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@CrossOrigin
public class Login {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
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
        loginResponse.setUserDTO(UserMapper.getInstance().toDTO(account.getUser()));
        loginResponse.setAccessToken(jwtToken);

        return loginResponse;
    }
    // Api /api/random yêu cầu phải xác thực mới có thể request

}
