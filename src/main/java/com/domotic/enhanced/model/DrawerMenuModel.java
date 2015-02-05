package com.domotic.enhanced.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * TODO
 */
@Entity(name = "T_DRAWER_MENU")
public class DrawerMenuModel {
  
  public DrawerMenuModel() {
    // needed by ormlite
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(nullable = false)
  private String label;// string.xml
  
  @Column(nullable = false)
  private Integer order;
  
  @Column(nullable = false)
  private String qualifiedName;

}
