package com.sungsan.gotoeat.interfaces;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sungsan.gotoeat.application.RestaurantService;
import com.sungsan.gotoeat.domain.MenuItem;
import com.sungsan.gotoeat.domain.Restaurant;
import com.sungsan.gotoeat.domain.RestaurantNotFoundException;
import com.sungsan.gotoeat.domain.Review;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RestaurantService restaurantService;

  //  @Test
  public void list() throws Exception {
    List<Restaurant> restaurants = new ArrayList<>();
    Restaurant restaurant = Restaurant.builder()
        .id(1L)
        .name("VIPS")
        .location("JINJU")
        .build();
    restaurants.add(restaurant);
    given(restaurantService.getRestaurants()).willReturn(restaurants);

    mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(
            "\"id\":1"
        )))
        .andExpect(content().string(containsString(
            "\"name\":\"VIPS\""
        )))
        .andExpect(content().string(containsString(
            "\"location\":\"JINJU\""
        )));

  }

  @Test
  public void listWithParams() throws Exception {
    List<Restaurant> restaurants = new ArrayList<>();
    Restaurant restaurant = Restaurant.builder()
        .id(1L)
        .categoryId(2L)
        .name("VIPS")
        .location("Seoul")
        .build();
    restaurants.add(restaurant);
    given(restaurantService.getRestaurants("Seoul", 2L)).willReturn(restaurants);

    mvc.perform(MockMvcRequestBuilders.get("/restaurants?region=Seoul&category=2"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(
            "\"id\":1"
        )))
        .andExpect(content().string(containsString(
            "\"name\":\"VIPS\""
        )))
        .andExpect(content().string(containsString(
            "\"location\":\"Seoul\""
        )));

  }

  @Test
  public void detailWithSuccess() throws Exception {
    Restaurant restaurant = Restaurant.builder()
        .id(1L)
        .name("VIPS")
        .location("SEOUL")
        .build();
    MenuItem menuItem = MenuItem.builder()
        .id(2L)
        .menu("Steak")
        .build();
    Review review = Review.builder().name("Leo").score(3).body("delicious").build();
    restaurant.addMenuItem(menuItem);
    restaurant.setReviews(Collections.singletonList(review));

    given(restaurantService.getRestaurant(1L)).willReturn(restaurant);
    mvc.perform(MockMvcRequestBuilders.get("/restaurants/1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"id\":1")))
        .andExpect(content().string(containsString("Steak")))
        .andExpect(content().string(containsString("delicious")));

  }

  @Test
  public void detailWithNotExist() throws Exception {
    given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));
    mvc.perform(MockMvcRequestBuilders.get("/restaurants/404"))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().string("{}"));
  }

}