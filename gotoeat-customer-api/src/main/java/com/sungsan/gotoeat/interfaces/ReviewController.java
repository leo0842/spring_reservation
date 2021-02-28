package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.ReviewService;
import com.sungsan.gotoeat.domain.Review;
import io.jsonwebtoken.Claims;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

  @Autowired
  ReviewService reviewService;

  @PostMapping("/restaurants/{restaurantId}/reviews")
  public ResponseEntity<Object> create(
      Authentication authentication,
      @Valid @RequestBody Review resource,
      @PathVariable("restaurantId") Long restaurantId
  ) throws URISyntaxException {
    Claims claims = (Claims) authentication.getPrincipal();

    String name = claims.get("userName", String.class);
    Integer score = resource.getScore();
    String body = resource.getBody();
    Review review = reviewService.addReview(restaurantId, name, score, body);
    String url = "/restaurants/" + restaurantId + "/reviews/" +review.getId();
    return ResponseEntity.created(new URI(url))
        .body("{}");

  }

}
