package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.User;
import com.sungsan.gotoeat.domain.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User authenticate(String email, String password) {

    User user = userRepository.findByEmail(email).orElseThrow(() -> new UnregisteredEmailException(email));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new WrongPasswordException();

    }

    return user;

  }
}
