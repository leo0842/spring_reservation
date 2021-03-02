package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.ReservationService;
import com.sungsan.gotoeat.domain.Reservation;
import io.jsonwebtoken.Claims;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @GetMapping("/reservations")
  public List<Reservation> create(
      Authentication authentication
  ) {
    Claims claims = (Claims) authentication.getPrincipal();

    Long restaurantId = claims.get("restaurantId", Long.class);

    System.out.println(restaurantId);
    List<Reservation> reservations = reservationService.getReservations(restaurantId);
    return reservations;
  }

}
