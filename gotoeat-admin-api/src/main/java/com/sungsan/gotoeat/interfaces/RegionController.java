package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.RegionService;
import com.sungsan.gotoeat.domain.Region;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegionController {

  @Autowired
  private RegionService regionService;

  @GetMapping("/regions")
  public List<Region> list() {

    List<Region> regions = regionService.getRegions();
    return regions;
  }


}
