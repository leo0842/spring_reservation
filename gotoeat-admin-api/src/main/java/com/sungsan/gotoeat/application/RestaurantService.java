package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.MenuItem;
import com.sungsan.gotoeat.domain.MenuItemRepository;
import com.sungsan.gotoeat.domain.Restaurant;
import com.sungsan.gotoeat.domain.RestaurantNotFoundException;
import com.sungsan.gotoeat.domain.RestaurantRepository;
import com.sungsan.gotoeat.domain.Review;
import com.sungsan.gotoeat.domain.ReviewRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

  //  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  public RestaurantService(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  public Restaurant getRestaurant(Long id) {
    Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    return restaurant;

  }

  public List<Restaurant> getRestaurants() {
    List<Restaurant> restaurants = restaurantRepository.findAll();
    return restaurants;
  }

  public Restaurant addRestaurant(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);

  }

  @Transactional
  public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
    Restaurant restaurant1 = restaurantRepository.findById(id).orElse(null);
    restaurant1.updateInfo(restaurant.getName(), restaurant.getLocation());
//    restaurant1.setName(restaurant.getName());
//    restaurant1.setLocation(restaurant.getLocation());
//    return restaurantRepository.save(restaurant1);
    return restaurant1;
  }
}
