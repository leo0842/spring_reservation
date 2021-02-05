package com.sungsan.gotoeat.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Restaurant {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String location;
  @Transient
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

  public void updateInfo(String name, String location) {
    this.name = name;
    this.location = location;
  }
}
