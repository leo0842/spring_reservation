package com.sungsan.gotoeat.interfaces;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sungsan.gotoeat.application.ReviewService;
import com.sungsan.gotoeat.domain.Review;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTests {

  @Autowired
  MockMvc mvc;

  @MockBean
  private ReviewService reviewService;

  @Test
  public void list() throws Exception {

    List<Review> reviews = new ArrayList<>();
    reviews.add(Review.builder().body("delicious").build());

    given(reviewService.getReviews()).willReturn(reviews);

    mvc.perform(get("/reviews"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("delicious")));
  }

}