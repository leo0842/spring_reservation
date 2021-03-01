package com.sungsan.gotoeat.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
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

  public String createToken(Long userId, String userName, Long restaurantId) {
    JwtBuilder builder = Jwts.builder()
        .claim("userId", userId)
        .claim("userName", userName);
    if (restaurantId != null) {
      builder = builder.claim("restaurantId", restaurantId);
    }
    return builder
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public Claims getClaims(String token) {

    JwtParser parser = Jwts.parserBuilder()
        .setSigningKey(key)
        .build();

    return parser.parseClaimsJws(token).getBody();

  }
}
