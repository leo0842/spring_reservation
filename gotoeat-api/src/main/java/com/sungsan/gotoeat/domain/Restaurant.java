package com.sungsan.gotoeat.domain;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
  private Long id;
  private String name;
  private String location;
  private List<MenuItem> menuItems = new ArrayList<>();

  public Restaurant() {
  }

  public Restaurant(Long id, String name, String location) {
    this.id = id;
    this.name = name;
    this.location = location;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public String getInformation() {
    return name + " in " + location;
  }

  public List<MenuItem> getMenuItems() {
    return menuItems;
  }

  public void addMenuItem(MenuItem menuItem) {
    menuItems.add(menuItem);
  }

  public void setMenuItems(List<MenuItem> menuItems) {
    for (MenuItem menuItem : menuItems){
      addMenuItem(menuItem);
    }
  }
}
