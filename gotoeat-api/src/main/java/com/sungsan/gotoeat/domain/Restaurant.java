package com.sungsan.gotoeat.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

  @Id
  @GeneratedValue
  private Long id;
  @NotEmpty
  private String name;
  @NotEmpty
  private String location;
  @Transient
  private List<MenuItem> menuItems;

  public String getInformation() {
    return name + " in " + location;
  }


  public void addMenuItem(MenuItem menuItem) {
    if (menuItems == null) {
      menuItems = new ArrayList<>();
    }
    menuItems.add(menuItem);
  }

  public void setMenuItems(List<MenuItem> menuItems) {
    for (MenuItem menuItem : menuItems) {
      addMenuItem(menuItem);
    }
  }

  public void updateInfo(String name, String location) {
    this.name = name;
    this.location = location;
  }
}
