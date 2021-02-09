package com.sungsan.gotoeat.interfaces;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sungsan.gotoeat.application.ReviewService;
import com.sungsan.gotoeat.domain.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
  public void createWithValid() throws Exception {
    given(reviewService.addReview(any(Review.class))).willReturn(Review.builder().id(123L).build()
    );
    mvc.perform(post("/restaurants/1/reviews")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"sungsan\",\"score\":3,\"body\":\"delicious\"}"))
        .andDo(print())
        .andExpect(content().string("{}"))
        .andExpect(status().isCreated())
        .andExpect(header().string("location", "/restaurants/1/reviews/123"));

    verify(reviewService).addReview(any());
  }
  @Test
  public void createWithInvalid() throws Exception {
    mvc.perform(post("/restaurants/1/reviews")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"score\":3,\"body\":\"delicious\"}"))
        .andDo(print())
        .andExpect(status().isBadRequest());

    verify(reviewService, never()).addReview(any());
  }

}