package com.anulengure5.websocialapp.config;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.net.Authenticator;

public class JwtProvider {
  private static  SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes()); // Generates a secure key

    public static String generateToken(Authentication auth){
        String jwToken = Jwts.builder()
                .setIssuer("Anurag")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000)) // 1 day expiration
                .claim("email", auth.getName())
                .signWith(secretKey) // specify algorithm
                .compact();

        return jwToken;
    }

    public static String getEmailFromJwtToken(String jwToken){
//        Token format - "Bearer'token'" so remove Bearer(7 length) substring

        jwToken=jwToken.substring(7);

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwToken)
                .getBody();



        String email=String.valueOf(claims.get("email"));
  
    return email;
    }

}
