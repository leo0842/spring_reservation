package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.UserService;
import com.sungsan.gotoeat.domain.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public List<User> list() {
    return userService.getUsers();
  }

  @PostMapping("/users")
  public ResponseEntity<?> create(
      @RequestBody User resource
  ) throws URISyntaxException {

    User user = userService.addUser(resource.getEmail(), resource.getName());

    String urlLocation = "/users/" + user.getId();
    return ResponseEntity.created(new URI(urlLocation)).body("{}");
  }

  @PatchMapping("/users/{id}")
  public String update(
      @PathVariable("id") Long id,
      @RequestBody User user
  ) {
    userService.updateUser(id, user);
    return "{}";
  }

  @DeleteMapping("/users/{id}")
  public String delete(
      @PathVariable("id") Long id
  ) {
    userService.deactivateUser(id);
    return "{}";

  }
  // TODO:
//  4. delete -> soft delete

}
