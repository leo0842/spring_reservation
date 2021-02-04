package com.sungsan.gotoeat.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

  private final List<Restaurant> restaurants = new ArrayList<>();

  public RestaurantRepositoryImpl(){
    restaurants.add(new Restaurant(1L, "VIPS", "SEOUL"));
    restaurants.add(new Restaurant(2L, "VIPS", "SEOUL"));
  }


  @Override
  public List<Restaurant> findAll() {

    return restaurants;
  }

  @Override
  public Restaurant findById(Long id) {

    return restaurants.stream().filter(restaurant -> restaurant.getId().equals(id)).findFirst().orElse(null); //findFirst 이유: List를 오브젝트로 돌리기 위해

 }

  @Override
  public Restaurant save(Restaurant restaurant) {
    restaurants.add(restaurant);
    return restaurant;
  }
}
