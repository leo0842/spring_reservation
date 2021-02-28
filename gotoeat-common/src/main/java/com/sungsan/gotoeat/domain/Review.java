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

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Review {

  @Id
  @GeneratedValue
  private Long id;

  @Setter
  private Long restaurantId;

  private String name;

  @NotNull
  private Integer score;

  private String body;


}
