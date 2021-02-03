package com.sungsan.gotoeat.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository {

  private final List<MenuItem> menuItems = new ArrayList<>();

  public MenuItemRepositoryImpl(){
    menuItems.add(new MenuItem(1L, 1L, "Steak"));
    menuItems.add(new MenuItem(2L, 2L, "Pasta"));
  }

  @Override
  public List<MenuItem> findByRestaurantId(Long restaurantId) {

    return menuItems.stream().filter(menuItem -> menuItem.getRestaurantId().equals(restaurantId)).collect(Collectors.toList());
  }
}
