package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.UserService;
import com.sungsan.gotoeat.domain.User;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/users")
  public ResponseEntity<?> create(
      @RequestBody User resource
  ) throws URISyntaxException {
    String email = resource.getEmail();
    String name = resource.getName();
    String password = resource.getPassword();
    User user = userService.registerUser(email, name, password);
    String url = "/users/" + 1; //TODO: user.getId로 고쳐야함
    return ResponseEntity.created(new URI(url)).body("{}");

  }

}
