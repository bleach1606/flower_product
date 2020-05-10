package ptit.edu.btl.service;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import ptit.edu.btl.constant.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;

import static java.util.Collections.emptyList;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days

    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String userId) {
        String JWT = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(SecurityConstants.JWT_SECRET.getBytes()))
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static String genToken(String userId) {
        return TOKEN_PREFIX + " " + Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(SecurityConstants.JWT_SECRET.getBytes()))
                .compact();
    }

    public static Authentication getAuthentication(HttpServletRequest request) throws AuthenticationException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            System.out.println("=> Authentication.getAuthentication.parse: ");
            String userId = Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(SecurityConstants.JWT_SECRET.getBytes()))
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            System.out.println("=> Authentication.getAuthentication: " + userId);
            return userId != null ?
                    new UsernamePasswordAuthenticationToken(userId, null, emptyList()) :
                    null;

        }
        return null;
    }
}
