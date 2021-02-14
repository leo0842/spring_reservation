package com.sungsan.gotoeat.interfaces;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sungsan.gotoeat.application.RestaurantService;
import com.sungsan.gotoeat.domain.Restaurant;
import com.sungsan.gotoeat.domain.RestaurantNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

  @Test
  public void createWithValidData() throws Exception {
    mvc.perform(MockMvcRequestBuilders.post("/restaurants")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Outback\",\"location\":\"Pusan\"}"))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(header().string("location", "/restaurants/1234"))
        .andExpect(content().string("{}"));

    verify(restaurantService).addRestaurant(any());
  }

  @Test
  public void createWithInvalidData() throws Exception {
    mvc.perform(MockMvcRequestBuilders.post("/restaurants")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"\",\"location\":\"\"}"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }


  @Test
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
  public void detailWithSuccess() throws Exception {
    Restaurant restaurant = Restaurant.builder()
        .id(1L)
        .name("VIPS")
        .location("SEOUL")
        .build();
// 이 부분은 다른 곳에서 확인 할 예정

    given(restaurantService.getRestaurant(1L)).willReturn(restaurant);
    mvc.perform(MockMvcRequestBuilders.get("/restaurants/1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"id\":1,\"name\":\"VIPS\",\"location\":\"SEOUL\"")));

  }

  @Test
  public void detailWithNotExist() throws Exception {
    given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));
    mvc.perform(MockMvcRequestBuilders.get("/restaurants/404"))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().string("{}"));
  }

  @Test
  public void updateWithValidData() throws Exception {
    mvc.perform(MockMvcRequestBuilders.patch("/restaurants/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Pasta\", \"location\":\"Ilsan\"}"))
        .andDo(print())
        .andExpect(status().isOk());

    verify(restaurantService).updateRestaurant(any(Long.class), any(Restaurant.class));

  }

  @Test
  public void updateWithInvalidData() throws Exception {
    mvc.perform(MockMvcRequestBuilders.patch("/restaurants/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"\", \"location\":\"\"}"))
        .andDo(print())
        .andExpect(status().isBadRequest());

//    verify(restaurantService).updateRestaurant(any(Long.class), any(Restaurant.class));

  }

}