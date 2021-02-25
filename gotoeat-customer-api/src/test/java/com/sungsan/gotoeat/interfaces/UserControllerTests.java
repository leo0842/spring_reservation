package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.UserService;
import com.sungsan.gotoeat.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService userService;



  @Test
  public void create() throws Exception {

    User mockUser = User.builder().id(1L).email("test@naver.com").name("tester").password("1234").build();
    given(userService.registerUser("test@naver.com", "tester", "1234")).willReturn(mockUser);

    mvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n"
            + "    \"email\":\"test@naver.com\", \"name\":\"tester\", \"password\":\"1234\"\n"
            + "  }"))
        .andExpect(status().isCreated())
    .andExpect(header().string("location", "/users/1"));

    verify(userService).registerUser(any(), any(), any());
  }

}
