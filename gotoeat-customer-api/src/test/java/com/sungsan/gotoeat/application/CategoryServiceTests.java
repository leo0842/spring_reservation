package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.sungsan.gotoeat.domain.Category;
import com.sungsan.gotoeat.domain.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

  }

  @Test
  public void getCategories() {

    List<Category> categories = categoryService.getCategories();

    assertEquals(categories.get(0).getName(), "Korean Food");
  }


}