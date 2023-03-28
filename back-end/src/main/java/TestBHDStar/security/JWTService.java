package TestBHDStar.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JWTService {
// Create JWT key form account information
    public String generateToken(AccountDetails accountDetails){
        Date now =new Date();
        Date expiratedDate = new Date(now.getTime()+SecurityConstant.EXPIRATION_TIME);
        // create json web token from account ID - this is JWT signature
        return Jwts.builder()
                .setSubject(Integer.toString(accountDetails.getAccount().getId()))
                .setIssuedAt(now)
                .setExpiration(expiratedDate)
                .signWith(Keys.hmacShaKeyFor(SecurityConstant.SECRET_KEY.getBytes()),SignatureAlgorithm.HS512)
                .compact();
    }
    // Get account information from jwt
    public int getAccountIdFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()// create parserBuilder object  to decoder
                .setSigningKey(SecurityConstant.SECRET_KEY.getBytes())//set Signing Key cho parseBuilder object
                .build()  // create JWT Parser to decode token
                .parseClaimsJws(token) // decode token and return JWS - Json web signature
                .getBody();// return Claims object contains information about authentication of JWT payload
        return Integer.parseInt(claims.getSubject());// return Subject of claims/ AccountID
    }
    public boolean isValidToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(SecurityConstant.SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return  true;
        } 
        catch (MalformedJwtException ex) {
           log.error("Invalid JWT token");
        }
        catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        }
        catch ( UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        }
        catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        }
        return  false;
    }
}
