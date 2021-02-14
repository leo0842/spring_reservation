package com.sungsan.gotoeat.interfaces;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sungsan.gotoeat.application.MenuItemService;
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
@WebMvcTest(MenuItemController.class)
class MenuItemControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private MenuItemService menuItemService;

  @Test
  public void bulkUpdate() throws Exception {
    mvc.perform(MockMvcRequestBuilders.patch("/restaurants/1/menuitems")
        .contentType(MediaType.APPLICATION_JSON)
        .content("[]"))
        .andDo(print())
        .andExpect(status().isOk());

    verify(menuItemService).bulkUpdate(any(Long.class), any());


  }

}