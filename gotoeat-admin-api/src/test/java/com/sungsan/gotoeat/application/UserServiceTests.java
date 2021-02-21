package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import com.sungsan.gotoeat.domain.User;
import com.sungsan.gotoeat.domain.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UserServiceTests {

  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  private void setUp() {
    openMocks(this);
    userService = new UserService(userRepository);
  }

  @Test
  public void getUsers() {

    List<User> mockUsers = new ArrayList<>();

    mockUsers.add(User.builder().email("test@naver.com").name("test").level(1).build());

    given(userRepository.findAll()).willReturn(mockUsers);

    List<User> users = userService.getUsers();

    assertEquals(users.get(0).getName(), "test");

  }

  @Test
  public void addUser() {

    String email = "test@daum.net";
    String name = "admin";

    User mockUser = User.builder().email(email).name(name).build();
    given(userRepository.save(any(User.class))).willReturn(mockUser);

    User user = userService.addUser(email, name);

    assertEquals(user.getName(), name);

    verify(userRepository).save(any(User.class));

  }

  @Test
  public void updateUser() {

    String email = "test@daum.net";
    String name = "admin";

    User mockUser = User.builder().email(email).name(name).level(300).build();
    given(userRepository.findById(1L)).willReturn(java.util.Optional.ofNullable(mockUser));

    User user = User.builder().email("test@daum.net").name("updateUser").level(3).build();

    User updateUser = userService.updateUser(1L, user);

    verify(userRepository).findById(1L);

    assertEquals(updateUser.getName(), "updateUser");
    assertTrue(updateUser.isAdmin());


  }

  @Test
  public void deactivateUser() {
    User mockUser = User.builder().email("test@naver.com").name("test").level(3).build();
    given(userRepository.findById(1L)).willReturn(Optional.ofNullable(mockUser));

    User deactivateUser = userService.deactivateUser(1L);
    verify(userRepository).findById(1L);

    assertFalse(deactivateUser.isActive());
  }
}