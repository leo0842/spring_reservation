package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.Category;
import com.sungsan.gotoeat.domain.CategoryRepository;
import com.sungsan.gotoeat.domain.Region;
import com.sungsan.gotoeat.domain.RegionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {


  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getCategories() {

    return categoryRepository.findAll();

  }

  public Category addCategory(Category category) {

    return categoryRepository.save(category);
  }
}