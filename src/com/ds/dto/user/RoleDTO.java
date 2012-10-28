package com.ds.dto.user;

/**
 * @author adlakha.vaibhav
 */
public class RoleDTO {

  private String name;

  private boolean selected;


  public RoleDTO(String name, boolean selected) {
    this.name = name;
    this.selected = selected;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }
}
