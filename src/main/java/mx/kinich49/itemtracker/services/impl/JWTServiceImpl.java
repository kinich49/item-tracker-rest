package mx.kinich49.itemtracker.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mx.kinich49.itemtracker.services.JWTService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTServiceImpl implements JWTService {

    private final String SECRET_KEY = "secret";
    private final long thirtyDaysInMillis = 2_592_000_000L;

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claimsResolver.apply(claims);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> userClaims = new HashMap<>();
        String username = userDetails.getUsername();
        List<String> authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        userClaims.put("authorities", authorities);

        return createToken(userClaims, username);
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return userDetails.getUsername().equals(username) &&
                isTokenNonExpired(token);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        Date expirationDate = new Date(System.currentTimeMillis() + thirtyDaysInMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(expirationDate)
                .setIssuedAt(new Date())
                .setIssuer("mx.kinich49")
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private boolean isTokenNonExpired(String token) {
        Date expirationDate = extractExpiration(token);
        Date today = new Date();

        return expirationDate
                .after(today);
    }

}
