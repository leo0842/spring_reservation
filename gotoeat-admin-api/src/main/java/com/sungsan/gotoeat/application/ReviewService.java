package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.Review;
import com.sungsan.gotoeat.domain.ReviewRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {



  ReviewRepository reviewRepository;


  @Autowired
  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }


  public List<Review> getReviews() {
    return (List<Review>) reviewRepository.findAll();
  }
}
