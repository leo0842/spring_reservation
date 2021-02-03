package com.sungsan.gotoeat.domain;

import java.util.List;

public interface MenuItemRepository {

  List<MenuItem> findByRestaurantId(Long restaurantId);

}
