package com.sungsan.gotoeat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JwtUtilTests {

  private final String secretKey = "123456789012345678901234567890123456";

  private JwtUtil jwtUtil;

  @BeforeEach
  public void setUp() {
    jwtUtil = new JwtUtil(secretKey);
  }

  @Test
  public void createToken() {

    Long userId = 1L;
    String userName = "Leo";
    Long restaurantId = 1L;
    String token = jwtUtil.createToken(userId, userName, restaurantId);

    System.out.println(token);

    assertTrue(token.contains("."));

  }

  @Test
  public void getClaims() {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsInVzZXJOYW1lIjoiTGVvIn0.CO12imVsXBzBLktFK0r3jPHzeScqV0deh9h2N16JVqM";
    Claims claims = jwtUtil.getClaims(token);

    assertEquals(claims.get("userName"),"Leo");
    assertEquals(claims.get("userId", Long.class),1L);
  }

}