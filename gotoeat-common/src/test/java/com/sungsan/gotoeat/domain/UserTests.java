package com.sungsan.gotoeat.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserTests {

  @Test
  public void creation() {
    User user = User.builder()
        .email("test@naver.com")
        .name("test")
        .level(3)
        .build();

    assertEquals(user.getName(), "test");
    assertTrue(user.isAdmin());

  }

  @Test
  public void deactivate() {
    User user = User.builder()
        .email("test@naver.com")
        .name("test")
        .level(3)
        .build();

    user.deactivate();

    assertFalse(user.isActive());
  }

}
