package com.sungsan.gotoeat.interfaces;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sungsan.gotoeat.application.UnregisteredEmailException;
import com.sungsan.gotoeat.application.UserService;
import com.sungsan.gotoeat.application.WrongPasswordException;
import org.hamcrest.Matchers;
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


  @Test
  public void createWithValid() throws Exception {

    given(userService.authenticate(any(), any())).willReturn(SessionResponseDto.builder().accessToken("AT").build());

    mvc.perform(post("/session")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"test@naver.com\", \"password\":\"1234\"}"))
        .andExpect(status().isCreated())
        .andExpect(header().string("location", "/session"))
        .andExpect(content().string("{\"accessToken\":\"AT\"}"));

    String email = "test@naver.com";
    String password = "1234";

    verify(userService).authenticate(email, password);

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