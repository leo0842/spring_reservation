package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.User;
import com.sungsan.gotoeat.domain.UserRepository;
import com.sungsan.gotoeat.interfaces.SessionResponseDto;
import com.sungsan.gotoeat.utils.JwtUtil;
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

  public User registerUser(String email, String name, String password) {

    Optional<User> existUser = userRepository.findByEmail(email);

    if (existUser.isPresent()) {
      throw new EmailExistException(email);
    }

    String encodedPassword = passwordEncoder.encode(password);

    User user = User.builder().email(email).name(name).password(encodedPassword).level(1).build();

    return userRepository.save(user);
  }

  public User authenticate(String email, String password) {

    User user = userRepository.findByEmail(email).orElseThrow(() -> new UnregisteredEmailException(email));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new WrongPasswordException();

    }

    return user;

  }
}
