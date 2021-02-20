package com.sungsan.gotoeat.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

  List<Restaurant> findAll();

  List<Restaurant> findAllByLocationContainingAndCategoryId(String region, Long categoryId);

}
