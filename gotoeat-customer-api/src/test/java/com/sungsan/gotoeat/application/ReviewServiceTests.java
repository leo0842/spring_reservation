package com.sungsan.gotoeat.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import com.sungsan.gotoeat.domain.Review;
import com.sungsan.gotoeat.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ReviewServiceTests {

  private ReviewService reviewService;
  @Mock
  private ReviewRepository reviewRepository;

  @BeforeEach
  public void setUp() {
    openMocks(this);
    reviewService = new ReviewService(reviewRepository);
  }

  @Test
  public void addReview() {

    reviewService.addReview(1L, "Leo", 3, "delicious");

    verify(reviewRepository).save(any());
  }
}