package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import com.sungsan.gotoeat.domain.User;
import com.sungsan.gotoeat.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

class UserServiceTests {

  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    openMocks(this);
    userService = new UserService(userRepository);
  }

  @Test
  public void registerUser() {
    String email = "test@naver.com";
    String name = "test";
    String password = "1234";
    User mockUser = User.builder().email(email).name(name).password(password).build();

    given(userRepository.save(any(User.class))).willReturn(mockUser);

    User user = userService.registerUser(email, name, password);

    assertEquals(user.getName(), "test");

    verify(userRepository).save(any());

  }

  @Test
  public void registerUserWithExistingUser() {
    String email = "test@naver.com";
    String name = "test";
    String password = "1234";
    User mockUser = User.builder().email(email).name(name).password(password).build();

    given(userRepository.findByEmail(email)).willReturn(java.util.Optional.ofNullable(mockUser));

    assertThrows(EmailExistException.class, () -> userService.registerUser(email, name, password));

  }
}