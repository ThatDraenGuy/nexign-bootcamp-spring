package com.draen.security.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${custom.jwt.secret}")
    private String jwtSecret;

    @Value("${custom.jwt.expirationMs}")
    private int expirationMs;

    public String generateJwtToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiration = new Date(now.getTime()+expirationMs);
        return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiration).signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes())).compact();
    }


    public String parseJwt(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "token");
        if (cookie==null) return null;
        return cookie.getValue();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseClaimsJws(token).getBody().getSubject();
    }
}