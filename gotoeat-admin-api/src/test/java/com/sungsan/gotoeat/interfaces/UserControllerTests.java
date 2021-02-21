package com.sungsan.gotoeat.interfaces;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sungsan.gotoeat.application.UserService;
import com.sungsan.gotoeat.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.ArrayEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService userService;

  @Test
  public void list() throws Exception {

    List<User> users = new ArrayList<>();

    users.add(User.builder().email("test@naver.com").name("test").level(1).build());

    given(userService.getUsers()).willReturn(users);

    mvc.perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("test")));

  }

  @Test
  public void create() throws Exception {

    String email = "test@daum.net";
    String name = "admin";

    User user = User.builder().email(email).name(name).build();
    given(userService.addUser(email, name)).willReturn(user);

    mvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\": \"test@daum.net\", \"name\": \"admin\"}"))
        .andExpect(status().isCreated())
        .andExpect(content().string("{}"));

    verify(userService).addUser(email, name);
  }

  @Test
  public void update() throws Exception {

    mvc.perform(patch("/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\": \"test@daum.net\", \"name\": \"updateUser\", \"level\": 3}"))
        .andExpect(status().isOk())
        .andExpect(content().string("{}"));

    verify(userService).updateUser(any(Long.class),any(User.class));
  }

  @Test
  public void deactivate() throws Exception {

    mvc.perform(delete("/users/1"))
        .andExpect(status().isOk());

    verify(userService).deactivateUser(1L);


  }
}