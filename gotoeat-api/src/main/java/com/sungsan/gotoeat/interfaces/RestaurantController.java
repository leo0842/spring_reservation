package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.RestaurantService;
import com.sungsan.gotoeat.domain.MenuItem;
import com.sungsan.gotoeat.domain.MenuItemRepository;
import com.sungsan.gotoeat.domain.Restaurant;
import com.sungsan.gotoeat.domain.RestaurantRepository;
import com.sungsan.gotoeat.domain.RestaurantRepositoryImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @GetMapping("/restaurants")
  public List<Restaurant> getRestaurants(){

    List<Restaurant> restaurants = restaurantService.getRestaurants();
    return restaurants;
  }

  @GetMapping("/restaurants/{id}")
  public Restaurant detailRestaurant(@PathVariable("id") Long id){
    Restaurant restaurant = restaurantService.getRestaurant(id);
//    List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
//    restaurant.setMenuItems(menuItems);
    return restaurant;
  }

}
