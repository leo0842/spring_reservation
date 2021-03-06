package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.Category;
import com.sungsan.gotoeat.domain.CategoryRepository;
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

}