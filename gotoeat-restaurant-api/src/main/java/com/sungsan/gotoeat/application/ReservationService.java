package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.Reservation;
import com.sungsan.gotoeat.domain.ReservationRepository;
import java.util.List;
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

  public List<Reservation> getReservations(Long restaurantId) {
    return reservationRepository.findAllByRestaurantId(restaurantId);
  }
}
