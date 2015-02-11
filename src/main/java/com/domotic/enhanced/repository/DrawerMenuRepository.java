package com.domotic.enhanced.repository;

import java.util.List;

import com.domotic.enhanced.model.DrawerMenuModel;

public interface DrawerMenuRepository {
  
  void initDrawerMenus();
  
  void add(DrawerMenuModel drawerMenu);
  
  List<DrawerMenuModel> findAll();

}
