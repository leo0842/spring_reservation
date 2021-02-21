package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.User;
import com.sungsan.gotoeat.domain.UserRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {

    return userRepository.findAll();
  }

  public User addUser(String email, String name) {
    User user = User.builder()
        .email(email)
        .name(name)
        .level(1)
        .build();
    return userRepository.save(user);

  }

  @Transactional
  public User updateUser(Long id, User user) {
    // TODO: 예외 처리
    User updatingUser = userRepository.findById(id).orElse(null);
    updatingUser.setEmail(user.getEmail());
    updatingUser.setName(user.getName());
    updatingUser.setLevel(user.getLevel());
    System.out.println(updatingUser.getEmail());
    System.out.println(updatingUser.getLevel());
    System.out.println(updatingUser.getName());

    return updatingUser;

  }

  @Transactional
  public User deactivateUser(Long id) {
    User deletedUser = userRepository.findById(id).orElse(null);
    deletedUser.deactivate();

    return deletedUser;

  }
}
