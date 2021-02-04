package com.sungsan.gotoeat.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RestaurantRepositoryImplTests {

  @Test
  void save() {
    RestaurantRepositoryImpl restaurantRepository = new RestaurantRepositoryImpl();
    int oldCount = restaurantRepository.findAll().size();
    restaurantRepository.save(new Restaurant());
    int newCount = restaurantRepository.findAll().size();
    assertEquals(oldCount+1, newCount);
  }
}