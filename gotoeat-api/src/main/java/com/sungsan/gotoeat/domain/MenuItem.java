package com.sungsan.gotoeat.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MenuItem {

  @Id
  @GeneratedValue
  private  Long id;
  private  Long restaurantId;
  private  String menu;

}
