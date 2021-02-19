package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.CategoryService;
import com.sungsan.gotoeat.domain.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/categories")
  public List<Category> list() {

    return categoryService.getCategories();
  }

}
