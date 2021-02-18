package com.sungsan.gotoeat.application;

import com.sungsan.gotoeat.domain.Region;
import com.sungsan.gotoeat.domain.RegionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {


  private RegionRepository regionRepository;

  @Autowired
  public RegionService(RegionRepository regionRepository) {
    this.regionRepository = regionRepository;
  }

  public List<Region> getRegions() {
    List<Region> regions = regionRepository.findAll();

    return regions;
  }

}