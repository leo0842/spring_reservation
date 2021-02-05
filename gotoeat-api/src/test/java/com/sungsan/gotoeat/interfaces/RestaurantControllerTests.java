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
import com.sungsan.gotoeat.domain.MenuItem;
import com.sungsan.gotoeat.domain.Restaurant;
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
  public void create() throws Exception {
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
  public void detail() throws Exception {
    Restaurant restaurant1 = Restaurant.builder()
        .id(1L)
        .name("VIPS")
        .location("SEOUL")
        .build();
    System.out.println(restaurant1.getName());
    restaurant1.addMenuItem(new MenuItem());
    Restaurant restaurant2 = Restaurant.builder()
        .id(2L)
        .name("VIPS")
        .location("SEOUL")
        .build();

    given(restaurantService.getRestaurant(1L)).willReturn(restaurant1);
    given(restaurantService.getRestaurant(2L)).willReturn(restaurant2);
    mvc.perform(MockMvcRequestBuilders.get("/restaurants/1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"id\":1,\"name\":\"VIPS\",\"location\":\"SEOUL\"")))
        .andExpect(content().string(containsString("Steak")));

    mvc.perform(MockMvcRequestBuilders.get("/restaurants/2"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"id\":2,\"name\":\"VIPS\",\"location\":\"SEOUL\"")));

  }

  @Test
  public void update() throws Exception {
    mvc.perform(MockMvcRequestBuilders.patch("/restaurants/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Pasta\", \"location\":\"Ilsan\"}"))
        .andDo(print())
        .andExpect(status().isOk());

    verify(restaurantService).updateRestaurant(any(Long.class), any(Restaurant.class));

  }

}