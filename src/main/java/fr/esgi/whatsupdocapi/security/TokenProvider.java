package fr.esgi.whatsupdocapi.security;

import fr.esgi.whatsupdocapi.security.user.Account;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    @Value("${token.secret.key:secret}")
    private String secretKey;
    private static final String AUTHORITIES_KEY = "auth";
    private final long tokenValidityInMilliseconds = Duration.ofMinutes(5).getSeconds() * 1000;


    public String createToken(Account account) {
        Claims claims = Jwts.claims();
        claims.put("email", account.getEmail());
        claims.put("role", account.getRole());

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseToken(token).getBody();

        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            parseToken(authToken);
            return true;

        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
            return false;
        }
    }

    private Jws<Claims> parseToken(String authToken) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(authToken);
    }
}
