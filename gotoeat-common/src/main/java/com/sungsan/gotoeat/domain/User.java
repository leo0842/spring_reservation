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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @NotEmpty
  @Setter
  private String email;

  @NotEmpty
  @Setter
  private String name;

  private String password;

  @NotNull
  @Setter
  private Integer level;

  public boolean isAdmin() {
    return level == 3;
  }

  public boolean isActive() {
    return level != 0;
  }

  public void deactivate() {
    level = 0;
  }
}
