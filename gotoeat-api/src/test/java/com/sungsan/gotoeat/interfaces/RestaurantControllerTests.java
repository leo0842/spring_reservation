package com.sungsan.gotoeat.interfaces;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sungsan.gotoeat.application.RestaurantService;
import com.sungsan.gotoeat.domain.MenuItemRepository;
import com.sungsan.gotoeat.domain.MenuItemRepositoryImpl;
import com.sungsan.gotoeat.domain.RestaurantRepository;
import com.sungsan.gotoeat.domain.RestaurantRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTests {

  @Autowired
  private MockMvc mvc;

  @SpyBean(RestaurantService.class)
  private RestaurantService restaurantService;

  @SpyBean(RestaurantRepositoryImpl.class)
  private RestaurantRepository restaurantRepository;

  @SpyBean(MenuItemRepositoryImpl.class)
  private MenuItemRepository menuItemRepository;


  @Test
  public void list() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(
            "\"id\":1"
        )))
        .andExpect(content().string(containsString(
            "\"name\":\"VIPS\""
        )))
        .andExpect(content().string(containsString(
            "\"location\":\"SEOUL\""
        )));

  }

  @Test
  public void detail() throws Exception {
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

}