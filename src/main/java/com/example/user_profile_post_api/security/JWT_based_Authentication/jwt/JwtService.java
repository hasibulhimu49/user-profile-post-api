package com.example.user_profile_post_api.security.JWT_based_Authentication.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    //private final String SECRET_KEY = "MY_SUPER_SECRET_KEY_123456789_123456789"; //32+ characters

    //Best practice
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${security.jwt.expiration}")
    private long EXPIRATION;


    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())   // token এ username রাখছি
                .setIssuedAt(new Date())                 // কখন token generate হল
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 ঘন্টা পরে expire
                .signWith(getSignKey(), SignatureAlgorithm.HS256) // secret key দিয়ে sign
                .compact();
    }


    public String extractUsername(String token)
    {
        return extractAllClaims(token).getSubject();
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }


    private Claims extractAllClaims(String token)
    {
        return Jwts.parserBuilder().setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();


    }


    private Key getSignKey()
    {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    }
}



