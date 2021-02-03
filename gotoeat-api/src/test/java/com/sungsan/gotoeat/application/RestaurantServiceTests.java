package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.*;

import com.sungsan.gotoeat.domain.MenuItem;
import com.sungsan.gotoeat.domain.MenuItemRepository;
import com.sungsan.gotoeat.domain.MenuItemRepositoryImpl;
import com.sungsan.gotoeat.domain.Restaurant;
import com.sungsan.gotoeat.domain.RestaurantRepository;
import com.sungsan.gotoeat.domain.RestaurantRepositoryImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestaurantServiceTests {

  private RestaurantRepository restaurantRepository;

  private MenuItemRepository menuItemRepository;

  private RestaurantService restaurantService;

  @BeforeEach
  public void setUp() {
    restaurantRepository = new RestaurantRepositoryImpl();
    menuItemRepository = new MenuItemRepositoryImpl();
    restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
  }

  @Test
  public void getRestaurant() {

    Restaurant restaurant = restaurantService.getRestaurant(1L);

    System.out.println(restaurant.getName());
    System.out.println(restaurant.getMenuItems());

    assertEquals(restaurant.getId(), 1);
  }

  @Test
  public void getRestaurants() {
    List<Restaurant> restaurants = restaurantService.getRestaurants();
    System.out.println(restaurants);
    assertEquals(restaurants.size(), 2);
  }


}