package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.UserService;
import com.sungsan.gotoeat.domain.User;
import com.sungsan.gotoeat.utils.JwtUtil;
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
  private JwtUtil jwtUtil;

  @Autowired
  private UserService userService;

  @PostMapping("/session")
  public ResponseEntity<SessionResponseDto> create(
      @RequestBody SessionRequestDto resource
  ) throws URISyntaxException {

    String email = resource.getEmail();
    String password = resource.getPassword();
    User user = userService.authenticate(email, password);
    String token = jwtUtil.createToken(user.getId(), user.getName(),
        user.isRestaurantOwner() ? user.getRestaurantId() : null);
    SessionResponseDto sessionResponseDto = SessionResponseDto.builder().accessToken(token).build();
    String url = "/session";
    return ResponseEntity.created(new URI(url)).body(sessionResponseDto);

  }


}
