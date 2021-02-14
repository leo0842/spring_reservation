package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.Review;
import com.sungsan.gotoeat.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {


  @Autowired
  ReviewRepository reviewRepository;

  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public Review addReview(Long restaurantId, Review review) {
    review.setRestaurantId(restaurantId);
    return reviewRepository.save(review);

  }
}
