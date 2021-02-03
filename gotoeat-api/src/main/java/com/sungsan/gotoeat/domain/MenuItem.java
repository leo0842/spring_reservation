package com.sungsan.gotoeat.domain;

public class MenuItem {

  private final Long id;
  private final Long restaurantId;
  private final String menu;

  public MenuItem(Long id, Long restaurantId, String menu) {

    this.id = id;
    this.restaurantId = restaurantId;
    this.menu = menu;
  }

  public Long getId() {
    return id;
  }

  public Long getRestaurantId() {
    return restaurantId;
  }

  public String getMenu(){
    return menu;
  }
}
