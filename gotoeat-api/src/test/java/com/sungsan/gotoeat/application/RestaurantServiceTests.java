package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.sungsan.gotoeat.domain.MenuItem;
import com.sungsan.gotoeat.domain.MenuItemRepository;
import com.sungsan.gotoeat.domain.MenuItemRepositoryImpl;
import com.sungsan.gotoeat.domain.Restaurant;
import com.sungsan.gotoeat.domain.RestaurantRepository;
import com.sungsan.gotoeat.domain.RestaurantRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RestaurantServiceTests {

  @Mock
  private RestaurantRepository restaurantRepository;

  @Mock
  private MenuItemRepository menuItemRepository;

  private RestaurantService restaurantService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    List<Restaurant> restaurants = new ArrayList<>();
    restaurants.add(new Restaurant(1L, "VIPS", "JINJU"));
    given(restaurantRepository.findAll()).willReturn(restaurants);

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
    assertEquals(restaurants.size(), 1);
  }


}