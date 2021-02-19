package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.sungsan.gotoeat.domain.Category;
import com.sungsan.gotoeat.domain.CategoryRepository;
import com.sungsan.gotoeat.domain.Region;
import com.sungsan.gotoeat.domain.RegionRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

class CategoryServiceTests {

  private CategoryService categoryService;

  @Mock
  private CategoryRepository categoryRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    categoryService = new CategoryService(categoryRepository);

    List<Category> mockCategories = new ArrayList<>();
    mockCategories.add(Category.builder().name("Korean Food").build());
    given(categoryRepository.findAll()).willReturn(mockCategories);

    Category mockCategory;
    mockCategory = Category.builder().name("Korean Food").build();
    given(categoryRepository.save(any(Category.class))).willReturn(mockCategory);

  }

  @Test
  public void getCategories() {

    List<Category> categories = categoryService.getCategories();

    assertEquals(categories.get(0).getName(), "Korean Food");
  }

  @Test
  public void addCategory() {

    Category category = categoryService.addCategory(Category.builder().name("Korean Food").build());

    verify(categoryRepository).save(any(Category.class));

    assertEquals(category.getName(), "Korean Food");

  }

}