package com.sungsan.gotoeat.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.sungsan.gotoeat.domain.MenuItem;
import com.sungsan.gotoeat.domain.MenuItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

class MenuItemServiceTests {

  private MenuItemService menuItemService;

  @Mock
  private MenuItemRepository menuItemRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    menuItemService = new MenuItemService(menuItemRepository);

  }

  @Test
  public void getMenuItems() {
    List<MenuItem> mockMenuItems = new ArrayList<>();
    mockMenuItems.add(MenuItem.builder().menu("Steak").build());

    given(menuItemRepository.findAllByRestaurantId(1L)).willReturn(mockMenuItems);

    List<MenuItem> menuItems = menuItemService.getMenuItems(1L);
    System.out.println(menuItems);
    assertEquals(menuItems.get(0).getMenu(), "Steak");

  }

  @Test
  public void bulkUpdate() {
    List<MenuItem> menuItems = new ArrayList<>();
    menuItems.add(MenuItem.builder()
        .menu("Steak")
        .build());
    menuItems.add(MenuItem.builder()
        .menu("Pasta")
        .build());
    menuItems.add(MenuItem.builder()
        .id(1L)
        .menu("Risotto")
        .deleted(true)
        .build());
    menuItemService.bulkUpdate(1L, menuItems);

    verify(menuItemRepository, times(2)).save(any());
    verify(menuItemRepository, times(1)).deleteById(any(Long.class));
  }

}