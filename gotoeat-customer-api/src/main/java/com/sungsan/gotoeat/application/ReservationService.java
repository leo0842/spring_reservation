package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.Reservation;
import com.sungsan.gotoeat.domain.ReservationRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReservationService {

  @Autowired
  private ReservationRepository reservationRepository;

  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  public Reservation addReservation(
      Long restaurantId, Long userId, String name, String date, String time, Integer partySize) {
    Reservation reservation = Reservation.builder()
        .restaurantId(restaurantId)
        .userId(userId)
        .name(name)
        .date(date)
        .time(time)
        .partySize(partySize)
        .build();

    return reservationRepository.save(reservation);
  }

}
