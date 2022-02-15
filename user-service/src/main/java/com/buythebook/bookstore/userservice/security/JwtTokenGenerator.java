package com.buythebook.bookstore.userservice.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenGenerator {

    @Value("${application.security.jwt-token-secret}")
    private String jwtSecret;

    @Value("${application.security.jwt-issuer}")
    private String jwtIssuer;

    @Value("${application.security.jwt-token-expiry}")
    private int jwtExpirationMs;

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenGenerator.class);

    public String generateJwtToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            logger.error("Invalid token", ex.getMessage());
        }
        return false;
    }

    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }
}
