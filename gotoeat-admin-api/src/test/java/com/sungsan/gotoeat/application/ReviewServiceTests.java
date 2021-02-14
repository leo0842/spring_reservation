package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import com.sungsan.gotoeat.domain.Review;
import com.sungsan.gotoeat.domain.ReviewRepository;
import java.util.ArrayList;
import java.util.List;
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
  public void list() {

    List<Review> mockReviews = new ArrayList<>();
    mockReviews.add(Review.builder().body("delicious").build());
    given(reviewRepository.findAll()).willReturn(mockReviews);

    List<Review> reviews = reviewService.getReviews();

    assertEquals(reviews.get(0).getBody(), "delicious");

  }
}