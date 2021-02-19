package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.CategoryService;
import com.sungsan.gotoeat.domain.Category;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/categories")
  public List<Category> list() {

    return categoryService.getCategories();
  }

  @PostMapping("/categories")
  public ResponseEntity<?> create(
      @RequestBody Category resource
  ) throws URISyntaxException {
    Category category = categoryService.addCategory(resource);
    URI uriLocation = new URI("/categories/" + category.getId());
    return ResponseEntity.created(uriLocation).body("{}");
  }
}
