package com.sungsan.gotoeat.interfaces;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sungsan.gotoeat.application.CategoryService;
import com.sungsan.gotoeat.domain.Category;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CategoryService categoryService;

  @Test
  public void list() throws Exception {

    List<Category> categories = new ArrayList<>();
    categories.add(Category.builder().name("Korean Food").build());
    given(categoryService.getCategories()).willReturn(categories);

    mvc.perform(get("/categories"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Korean Food")));
  }


  @Test
  public void create() throws Exception {
    given(categoryService.addCategory(any(Category.class))).willReturn(Category.builder().name("Korean Food").build());

    mvc.perform(post("/categories")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Korean Food\"}"))
        .andExpect(status().isCreated())
        .andExpect(content().string("{}"));
    verify(categoryService).addCategory(any(Category.class));
  }

}