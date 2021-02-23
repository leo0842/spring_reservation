package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.User;
import com.sungsan.gotoeat.domain.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {

    this.userRepository = userRepository;

  }

  public User registerUser(String email, String name, String password) {

    Optional<User> existUser = userRepository.findByEmail(email);

    if (existUser.isPresent()) {
      throw new EmailExistException(email);
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(password);
    User user = User.builder().email(email).name(name).password(encodedPassword).level(1).build();

    return userRepository.save(user);
  }
}
