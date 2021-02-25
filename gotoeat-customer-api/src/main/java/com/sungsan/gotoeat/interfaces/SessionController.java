package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.UserService;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

  @Autowired
  private UserService userService;

  @PostMapping("/session")
  public ResponseEntity<SessionResponseDto> create(
      @RequestBody SessionRequestDto resource
  ) throws URISyntaxException {

    String email = resource.getEmail();
    String password = resource.getPassword();
    String url = "/session";
//    SessionDto.builder().accessToken(accessToken).build();
    SessionResponseDto sessionResponseDto = userService.authenticate(email, password);
    return ResponseEntity.created(new URI(url)).body(sessionResponseDto);

  }


}
