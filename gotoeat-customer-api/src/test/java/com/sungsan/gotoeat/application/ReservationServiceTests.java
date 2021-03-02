package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.sungsan.gotoeat.domain.Reservation;
import com.sungsan.gotoeat.domain.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ReservationServiceTests {

  private ReservationService reservationService;

  @Mock
  private ReservationRepository reservationRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    reservationService = new ReservationService(reservationRepository);
  }

  @Test
  public void addReservation() {

    Long restaurantId = 1L;
    Long userId = 1L;
    String name = "Leo";
    String date = "2021-05-01";
    String time = "20:00";
    Integer partySize = 5;

    given(reservationRepository.save(any(Reservation.class))).will(invocation -> invocation.<Reservation>getArgument(0));


    Reservation reservation = reservationService.addReservation(restaurantId, userId, name, date, time, partySize);

    assertEquals(reservation.getName(), name);

    verify(reservationRepository).save(any(Reservation.class));

  }

}