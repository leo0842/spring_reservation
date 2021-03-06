package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.RegionService;
import com.sungsan.gotoeat.domain.Region;
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
public class RegionController {

  @Autowired
  private RegionService regionService;

  @GetMapping("/regions")
  public List<Region> list() {

    List<Region> regions = regionService.getRegions();
    return regions;
  }

  @PostMapping("/regions")
  public ResponseEntity<?> create(
      @RequestBody Region resource
  ) throws URISyntaxException {
    Region region = regionService.addRegion(resource);
    URI uriLocation = new URI("/regions/" + region.getId());
    return ResponseEntity.created(uriLocation).body("{}");
  }
}
