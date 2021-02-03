package com.sungsan.gotoeat.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RestaurantTests {

  private final Restaurant restaurant = new Restaurant(1L, "VIPS", "SEOUL");

  @Test
  public void creation() {

    assertEquals(restaurant.getId(), 1);
    assertEquals(restaurant.getName(), "VIPS");
    assertEquals(restaurant.getLocation(), "SEOUL");
  }

  @Test
  public void information() {
    assertEquals(restaurant.getInformation(), "VIPS in SEOUL");
  }

}