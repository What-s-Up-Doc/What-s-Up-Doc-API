package fr.esgi.whatsupdocapi.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider {

    @Value("${token.secret.key:secret}")
    private String secretKey;
    private final long tokenValidityInMilliseconds = Duration.ofMinutes(5).getSeconds() * 1000;
    private final DomainUserDetailsService domainUserDetailsService;

    public TokenProvider(DomainUserDetailsService domainUserDetailsService) {
        this.domainUserDetailsService = domainUserDetailsService;
    }


    public String createToken(String email, String role) {

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, this.secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseToken(token).getBody();
        UserDetails userDetails = this.domainUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
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
                .setSigningKey(this.secretKey)
                .parseClaimsJws(authToken);
    }
}
