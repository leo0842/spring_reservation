package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.MenuItem;
import com.sungsan.gotoeat.domain.MenuItemRepository;
import com.sungsan.gotoeat.domain.Restaurant;
import com.sungsan.gotoeat.domain.RestaurantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

  @Autowired
  RestaurantRepository restaurantRepository;

  @Autowired
  MenuItemRepository menuItemRepository;

  public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
    this.menuItemRepository = menuItemRepository;
    this.restaurantRepository = restaurantRepository;
  }

  public Restaurant getRestaurant(Long id) {
    Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
    List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
    restaurant.setMenuItems(menuItems);
    return restaurant;

  }

  public List<Restaurant> getRestaurants() {
    List<Restaurant> restaurants = restaurantRepository.findAll();
    return restaurants;
  }

  public Restaurant addRestaurant(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);

  }
}
