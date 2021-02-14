package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.RestaurantService;
import com.sungsan.gotoeat.domain.Restaurant;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
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

  @PostMapping("/restaurants")
  public ResponseEntity<?> addRestaurant(@Valid @RequestBody Restaurant resource) throws URISyntaxException {
    String name = resource.getName();
    String location = resource.getLocation();
    Restaurant restaurant = Restaurant.builder()
        .id(1234L)
        .name(name)
        .location(location)
        .build();
    restaurantService.addRestaurant(restaurant);
    URI uriLocation = new URI("/restaurants/"+restaurant.getId());
    return ResponseEntity.created(uriLocation).body("{}");
  }

  @PatchMapping("/restaurants/{id}")
  public String updateRestaurant(@PathVariable("id") Long id,@Valid @RequestBody Restaurant restaurant) {
    restaurantService.updateRestaurant(id, restaurant);
    return "{}";
  }

}
