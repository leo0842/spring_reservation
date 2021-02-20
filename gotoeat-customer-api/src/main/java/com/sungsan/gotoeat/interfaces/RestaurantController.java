package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.RestaurantService;
import com.sungsan.gotoeat.domain.Restaurant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @GetMapping("/restaurants")
  public List<Restaurant> getRestaurants(
      @RequestParam("region") String region,
      @RequestParam("category") Long categoryId) {

      return restaurantService.getRestaurants(region, categoryId);
  }

  @GetMapping("/restaurants/{id}")
  public Restaurant detailRestaurant(@PathVariable("id") Long id) {
    Restaurant restaurant = restaurantService.getRestaurant(id);
//    List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
//    restaurant.setMenuItems(menuItems);
    return restaurant;
  }

}
