package com.sungsan.gotoeat.interfaces;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sungsan.gotoeat.application.RegionService;
import com.sungsan.gotoeat.domain.Region;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegionController.class)
class RegionControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RegionService regionService;

  @Test
  public void list() throws Exception {

    List<Region> regions = new ArrayList<>();
    regions.add(Region.builder().name("Seoul").build());
    given(regionService.getRegions()).willReturn(regions);

    mvc.perform(get("/regions"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Seoul")));
  }


}