package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.sungsan.gotoeat.domain.Region;
import com.sungsan.gotoeat.domain.RegionRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

  @Test
  public void addRegion() {
    Region mockRegion;
    mockRegion = Region.builder().name("Seoul").build();
    given(regionRepository.save(any(Region.class))).willReturn(mockRegion);

    Region region = regionService.addRegion(Region.builder().name("Seoul").build());

    verify(regionRepository).save(any(Region.class));

    assertEquals(region.getName(), "Seoul");

  }

}