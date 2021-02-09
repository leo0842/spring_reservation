package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.ReviewService;
import com.sungsan.gotoeat.domain.Review;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

  @Autowired
  ReviewService reviewService;

  @PostMapping("/restaurants/{restaurantId}/reviews")
  public ResponseEntity<Object> create(@Valid @RequestBody Review resource,
      @PathVariable("restaurantId") Long restaurantId) throws URISyntaxException {
    Review review = reviewService.addReview(resource);
    String url = "/restaurants/" + restaurantId + "/reviews/" +review.getId();
    System.out.println(url);
    return ResponseEntity.created(new URI(url))
        .body("{}");

  }

}
