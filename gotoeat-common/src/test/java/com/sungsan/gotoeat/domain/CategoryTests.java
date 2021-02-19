package com.sungsan.gotoeat.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryTests {

  @Test
  public void creation() {
    Category category = Category.builder().name("Korean Food").build();

    assertEquals(category.getName(), "Korean Food");
  }



}