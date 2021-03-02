package com.sungsan.gotoeat.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import com.sungsan.gotoeat.application.ReservationService;
import com.sungsan.gotoeat.domain.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReservationController.class)
class ReservationControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ReservationService reservationService;

  @Test
  public void create() throws Exception {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsInVzZXJOYW1lIjoiTGVvIn0.CO12imVsXBzBLktFK0r3jPHzeScqV0deh9h2N16JVqM";

    Long restaurantId = 1L;
    Long userId = 1L;
    String name = "Leo";
    String date = "2021-05-01";
    String time = "20:00";
    Integer partySize = 5;

    Reservation mockReservation = Reservation.builder()
        .id(1L).userId(userId).name(name).date(date).time(time).partySize(partySize)
        .build();

    given(reservationService.addReservation(restaurantId, userId, name, date, time, partySize)).willReturn(mockReservation);


    mvc.perform(post("/restaurants/1/reservations")
        .header("Authorization", "Bearer " + token)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"date\":\"2021-05-01\", \"time\":\"20:00\", \"partySize\":5}"))
        .andDo(print())
        .andExpect(header().string("location", "/restaurants/1/reservations/reservations/1"))
        .andExpect(status().isCreated())
        .andExpect(content().string("{}"));

    verify(reservationService).addReservation(
        restaurantId, userId, name, date, time, partySize);

    String wrongName = "Leo2";
    verify(reservationService, never()).addReservation(
        restaurantId, userId, wrongName, date, time, partySize);

    Reservation reservation = reservationService.addReservation(restaurantId, userId, name, date, time, partySize);

    assertEquals(reservation.getName(), "Leo");
  }


}