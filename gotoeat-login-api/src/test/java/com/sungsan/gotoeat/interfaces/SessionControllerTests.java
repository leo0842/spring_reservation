package com.sungsan.gotoeat.interfaces;


import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sungsan.gotoeat.application.UnregisteredEmailException;
import com.sungsan.gotoeat.application.UserService;
import com.sungsan.gotoeat.application.WrongPasswordException;
import com.sungsan.gotoeat.domain.User;
import com.sungsan.gotoeat.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SessionController.class)
class SessionControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService userService;

  @MockBean
  private JwtUtil jwtUtil;


  @Test
  public void createWithValid() throws Exception {

    User mockUser = User.builder().id(1L).email("test@naver.com").name("Leo").level(1).build();
    given(userService.authenticate(any(), any())).willReturn(mockUser);
    given(jwtUtil.createToken(1L, "Leo", null)).willReturn("header.payload.signature");

    mvc.perform(post("/session")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"test@naver.com\", \"password\":\"1234\"}"))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(header().string("location", "/session"))
        .andExpect(content().string(containsString("{\"accessToken\":")))
        .andExpect(content().string(containsString(".")));

    String email = "test@naver.com";
    String password = "1234";

    verify(userService).authenticate(email, password);
    verify(jwtUtil).createToken(1L, "Leo", null);
    verify(jwtUtil, never()).createToken(1L, "Leo", 1L);

  }

  @Test
  public void createWithRestaurantOwner() throws Exception {

    User mockUser = User.builder().id(1L).name("Leo").email("test@naver.com").level(50).restaurantId(1L).build();
    given(userService.authenticate("test@naver.com", "1234")).willReturn(mockUser);
    given(jwtUtil.createToken(1L, "Leo", 1L)).willReturn("header.payload.signature");

    mvc.perform(post("/session")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"test@naver.com\", \"password\":\"1234\"}"))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(header().string("location", "/session"))
        .andExpect(content().string(containsString("{\"accessToken\":")))
        .andExpect(content().string(containsString(".")));

    String email = "test@naver.com";
    String password = "1234";

    verify(userService).authenticate(email, password);
    verify(jwtUtil).createToken(1L, "Leo", 1L);
    verify(jwtUtil, never()).createToken(1L, "Leo", null);

  }

  @Test
  public void createWithUnregisteredEmail() throws Exception {

    given(userService.authenticate(any(), any())).willThrow(UnregisteredEmailException.class);

    mvc.perform(post("/session")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"test@naver.net\", \"password\":\"1234\"}"))
        .andExpect(status().isBadRequest());

    String email = "test@naver.net";
    String password = "1234";

    verify(userService).authenticate(email, password);


  }

  @Test
  public void createWithWrongPassword() throws Exception {

    given(userService.authenticate(any(), any())).willThrow(WrongPasswordException.class);

    mvc.perform(post("/session")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"test@naver.com\", \"password\":\"4321\"}"))
        .andExpect(status().isBadRequest());

    String email = "test@naver.com";
    String password = "4321";

    verify(userService).authenticate(email, password);


  }

}