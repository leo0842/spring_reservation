package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.MenuItem;
import com.sungsan.gotoeat.domain.MenuItemRepository;
import com.sungsan.gotoeat.domain.RestaurantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService {


  MenuItemRepository menuItemRepository;
  @Autowired
  public MenuItemService(MenuItemRepository menuItemRepository){
    this.menuItemRepository = menuItemRepository;
  }


  public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
    for (MenuItem menuItem : menuItems){
      update(restaurantId, menuItem);
    }

  }

  private void update(Long restaurantId, MenuItem menuItem) {
    if (menuItem.isDeleted()){
      menuItemRepository.deleteById(menuItem.getId());
      return;
    }
    menuItem.setRestaurantId(restaurantId);
    menuItemRepository.save(menuItem);
  }
}
