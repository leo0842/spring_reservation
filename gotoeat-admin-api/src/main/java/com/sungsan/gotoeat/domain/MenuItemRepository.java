package com.sungsan.gotoeat.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

  List<MenuItem> findByRestaurantId(Long restaurantId);

  List<MenuItem> findAllByRestaurantId(Long restaurantId);
}
