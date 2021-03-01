package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import com.sungsan.gotoeat.domain.User;
import com.sungsan.gotoeat.domain.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserCustomerServiceTests {

  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @BeforeEach
  public void setUp() {
    openMocks(this);
    userService = new UserService(userRepository, passwordEncoder);
  }

  @Test
  public void authenticateWithValid() {
    String email = "test@naver.com";
    String password = "1234";

    User mockUser = User.builder().email(email).build();
    given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(mockUser));
    given(passwordEncoder.matches(any(), any())).willReturn(true);

    User user = userService.authenticate(email, password);

  }

  @Test
  public void authenticateWithUnregisteredEmail() {
    String email = "test@naver.net";
    String password = "1234";

    given(userRepository.findByEmail(email)).willThrow(UnregisteredEmailException.class);

    assertThrows(UnregisteredEmailException.class, () -> userService.authenticate(email, password));

  }

  @Test
  public void authenticateWithWrongPassword() {
    String email = "test@naver.com";
    String password = "4321";

    User mockUser = User.builder().email(email).password(password).build();
    given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(mockUser));
    given(passwordEncoder.matches(any(), any())).willReturn(false);

    assertThrows(WrongPasswordException.class, () -> userService.authenticate(email, password));

  }
}