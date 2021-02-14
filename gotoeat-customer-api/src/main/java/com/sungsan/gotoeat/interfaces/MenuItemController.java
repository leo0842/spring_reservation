package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.MenuItemService;
import com.sungsan.gotoeat.domain.MenuItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuItemController {

  @Autowired
  private MenuItemService menuItemService;

  @PatchMapping("restaurants/{restaurantId}/menuitems")
  public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId, @RequestBody List<MenuItem> menuItems){

    menuItemService.bulkUpdate(restaurantId, menuItems);
    return "";
  }

}
