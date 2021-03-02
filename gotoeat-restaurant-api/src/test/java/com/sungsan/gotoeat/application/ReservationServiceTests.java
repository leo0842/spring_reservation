package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.sungsan.gotoeat.domain.Reservation;
import com.sungsan.gotoeat.domain.ReservationRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

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
  public void getReservations() {
    Long restaurantId = 1L;

    List<Reservation> reservations = reservationService.getReservations(restaurantId);

    System.out.println(reservations);
    verify(reservationRepository).findAllByRestaurantId(restaurantId);

  }

}