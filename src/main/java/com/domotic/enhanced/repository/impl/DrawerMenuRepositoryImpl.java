package com.domotic.enhanced.repository.impl;

import java.util.List;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.res.StringArrayRes;
import org.apache.commons.collections4.CollectionUtils;

import com.domotic.enhanced.R;
import com.domotic.enhanced.database.DatabaseHelper;
import com.domotic.enhanced.model.DrawerMenuModel;
import com.domotic.enhanced.repository.DrawerMenuRepository;
import com.j256.ormlite.dao.RuntimeExceptionDao;

@EBean
public class DrawerMenuRepositoryImpl implements DrawerMenuRepository {
  
  @OrmLiteDao(helper = DatabaseHelper.class)
  RuntimeExceptionDao<DrawerMenuModel, Long> dao;
  
  @StringArrayRes(R.array.drawer_menu_label)
  String[] drawerMenuValues;
  
  @StringArrayRes(R.array.drawer_menu_class)
  String[] drawerMenuClass;

  @Override
  public void initDrawerMenus() {
    for (int i = 0; i < drawerMenuValues.length; i++) {
      DrawerMenuModel model = new DrawerMenuModel();
      model.setLabel(drawerMenuValues[i]);
      model.setOrder(i);
      model.setQualifiedName(drawerMenuClass[i]);
      add(model);
    }
  }

  @Override
  public void add(DrawerMenuModel drawerMenu) {
    dao.create(drawerMenu);
  }

  @Override
  public List<DrawerMenuModel> findAll() {
    List<DrawerMenuModel> list = dao.queryForAll();
    if (CollectionUtils.isEmpty(list)) {
      // only first time
      initDrawerMenus();
      list = dao.queryForAll();
    }
    return list;
  }

}
