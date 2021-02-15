package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.sungsan.gotoeat.domain.Region;
import com.sungsan.gotoeat.domain.RegionRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

class RegionServiceTests {

  private RegionService regionService;

  @Mock
  private RegionRepository regionRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    regionService = new RegionService(regionRepository);

  }

  @Test
  public void getRegions() {
    List<Region> mockRegions = new ArrayList<>();
    mockRegions.add(Region.builder().name("Seoul").build());
    given(regionRepository.findAll()).willReturn(mockRegions);

    List<Region> regions = regionService.getRegions();
    assertEquals(regions.get(0).getName(), "Seoul");
  }

}