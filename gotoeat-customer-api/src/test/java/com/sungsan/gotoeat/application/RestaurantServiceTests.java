package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.sungsan.gotoeat.domain.MenuItemRepository;
import com.sungsan.gotoeat.domain.Restaurant;
import com.sungsan.gotoeat.domain.RestaurantNotFoundException;
import com.sungsan.gotoeat.domain.RestaurantRepository;
import com.sungsan.gotoeat.domain.Review;
import com.sungsan.gotoeat.domain.ReviewRepository;
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

  @Mock
  private ReviewRepository reviewRepository;

  private RestaurantService restaurantService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    List<Restaurant> restaurants = new ArrayList<>();
    Restaurant restaurant1 = Restaurant.builder()
        .id(1L)
        .categoryId(2L)
        .name("VIPS")
        .location("JINJU")
        .build();
    restaurants.add(restaurant1);
    given(restaurantRepository.findAll()).willReturn(restaurants);
    given(restaurantRepository.findById(1L)).willReturn(
        java.util.Optional.of(restaurants.stream().filter(restaurant -> restaurant.getId().equals(1L)).findFirst().get()));
//    given(restaurantRepository.save(restaurant1)).willReturn(restaurant1); 이건 왜 addRestaurant()에서 인식하지 못할
    mockReviewRepository();
    restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);
  }

  private void mockReviewRepository() {

    List<Review> reviews = new ArrayList<>();
    reviews.add(Review.builder().name("Leo").score(3).body("delicious").build());
    given(reviewRepository.findByRestaurantId(1L)).willReturn(reviews);
  }


  @Test
  public void getRestaurantWithSuccess() {

    Restaurant restaurant = restaurantService.getRestaurant(1L);

    verify(menuItemRepository).findByRestaurantId(1L);
    verify(reviewRepository).findByRestaurantId(1L);

    Review review = restaurant.getReviews().get(0);
    assertEquals(restaurant.getId(), 1);
    assertEquals(review.getName(), "Leo");
  }

  @Test
  public void getRestaurantWithFail() {

    assertThrows(RestaurantNotFoundException.class, () -> restaurantService.getRestaurant(404L));

  }

  @Test
  public void getRestaurants() {
    List<Restaurant> restaurants = restaurantService.getRestaurants();
    System.out.println(restaurants);
    assertEquals(restaurants.size(), 1);
  }

  @Test
  public void getRestaurantsWithParams() {
    List<Restaurant> mockRestaurants = new ArrayList<>();
    mockRestaurants.add(Restaurant.builder().name("VIPS").categoryId(2L).location("Seoul").build());

    given(restaurantRepository.findAllByLocationContainingAndCategoryId("Seoul", 2L)).willReturn(mockRestaurants);

    List<Restaurant> restaurants = restaurantService.getRestaurants("Seoul", 2L);

    System.out.println(restaurants);
    assertEquals(restaurants.size(), 1);
    assertEquals(restaurants.get(0).getLocation(), "Seoul");
  }

  @Test
  public void addRestaurant() {
    System.out.println(1);
    given(restaurantRepository.save(any(Restaurant.class))).will(invocation -> { //addRestaurant가 호출 된 뒤에 이 메소드가 실행됨!
      System.out.println(invocation);
      Restaurant restaurant = invocation.getArgument(0);
      System.out.println(restaurant);
      return restaurant;
    });
    System.out.println(2);
    Restaurant restaurant = Restaurant.builder()
        .id(3L)
        .name("Outback")
        .location("Seoul")
        .build();

    System.out.println(3);
//    given(restaurantRepository.save(restaurant)).willReturn(restaurant);

    Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);

    System.out.println(4);
    assertEquals(createdRestaurant.getName(), "Outback");
//    assertEquals(oldCount + 1, newCount); 이거는 서비스에서 할 테스트가 아님. 레포에서 하는거;
  }

  @Test
  public void updateRestaurant() {
    Restaurant restaurant = Restaurant.builder()
        .id(1L)
        .name("VIPS")
        .location("Seoul")
        .build();
    Restaurant updatedRestaurant = Restaurant.builder()
        .id(1L)
        .name("Outback")
        .location("Seoul")
        .build();

    given(restaurantRepository.findById(1L)).willReturn(java.util.Optional.of(restaurant));

    Restaurant updated = restaurantService.updateRestaurant(1L, updatedRestaurant);
    assertEquals(updatedRestaurant.getName(), "Outback");
  }

}