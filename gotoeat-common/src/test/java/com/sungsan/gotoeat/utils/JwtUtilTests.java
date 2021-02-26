package com.sungsan.gotoeat.utils;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BooleanSupplier;
import org.junit.jupiter.api.Test;

class JwtUtilTests {

  @Test
  public void createToken() {

    String secretKey = "123456789012345678901234567890123456";
    JwtUtil jwtUtil = new JwtUtil(secretKey);

    Long userId = 1L;
    String userName = "Leo";
    String token = jwtUtil.createToken(userId, userName);

    assertTrue(token.contains("."));

  }

}