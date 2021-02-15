package com.sungsan.gotoeat.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegionTests {

  @Test
  public void create() {
    Region region = Region.builder().name("서울").build();

    assertEquals(region.getName(), "서울");
  }

}