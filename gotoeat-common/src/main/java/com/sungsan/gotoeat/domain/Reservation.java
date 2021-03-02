package com.sungsan.gotoeat.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

  @Id
  @GeneratedValue
  private Long id;

  private Long restaurantId;

  private Long userId;

  private String name;

  @NotEmpty
  private String date;

  @NotEmpty
  private String time;

  @NotNull
  private Integer partySize;

}
