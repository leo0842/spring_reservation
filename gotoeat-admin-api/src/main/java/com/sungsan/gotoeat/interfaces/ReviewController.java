package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.ReviewService;
import com.sungsan.gotoeat.domain.Review;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

  @Autowired
  ReviewService reviewService;

  @GetMapping("/reviews")
  public List<Review> list(){
    return reviewService.getReviews();

  }

}
