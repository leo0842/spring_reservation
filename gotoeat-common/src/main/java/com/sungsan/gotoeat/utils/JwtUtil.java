package com.sungsan.gotoeat.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import org.springframework.stereotype.Component;

public class JwtUtil {

  Key key;

  public JwtUtil(String secretKey) {
    this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  public String createToken(Long userId, String userName) {
    String token = Jwts.builder()
        .claim("userId", userId)
        .claim("userName", userName)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
    System.out.println(token);
    return token;
  }
}
