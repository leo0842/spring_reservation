package com.sungsan.gotoeat.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import com.sungsan.gotoeat.application.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReservationController.class)
class ReservationControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ReservationService reservationService;

  @Test
  public void list() throws Exception {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsInVzZXJOYW1lIjoiTGVvIiwicmVzdGF1cmFudElkIjoxfQ.WiMCrkjXF0w-mJR3Me443aLaQcXm5whzQxu7mJjGfYw";

    mvc.perform(get("/reservations")
    .header("Authorization", "Bearer " + token))
        .andDo(print())
        .andExpect(status().isOk());

    Long restaurantId = 1L;
    verify(reservationService).getReservations(restaurantId);

  }

}