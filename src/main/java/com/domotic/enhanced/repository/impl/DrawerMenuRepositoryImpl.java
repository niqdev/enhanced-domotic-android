package com.domotic.enhanced.repository.impl;

import java.sql.SQLException;
import java.util.List;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.res.StringRes;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domotic.enhanced.R;
import com.domotic.enhanced.database.DatabaseHelper;
import com.domotic.enhanced.model.DrawerMenuModel;
import com.domotic.enhanced.repository.DrawerMenuRepository;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.j256.ormlite.dao.RuntimeExceptionDao;

@EBean
public class DrawerMenuRepositoryImpl implements DrawerMenuRepository {
  
  private static final Logger log = LoggerFactory.getLogger(DrawerMenuRepositoryImpl.class);
  
  @OrmLiteDao(helper = DatabaseHelper.class)
  RuntimeExceptionDao<DrawerMenuModel, Long> dao;
  
  @StringArrayRes(R.array.drawer_menu_label)
  String[] drawerMenuValues;
  
  @StringArrayRes(R.array.drawer_menu_class)
  String[] drawerMenuClass;
  
  @StringRes(R.string.app_name)
  String appName;

  @Override
  public void initDrawerMenus() {
    for (int i = 0; i < drawerMenuValues.length; i++) {
      DrawerMenuModel model = new DrawerMenuModel();
      model.setLabel(drawerMenuValues[i]);
      // home default label
      model.setActionBarTitle(i == 0 ? appName : drawerMenuValues[i]);
      model.setOrderMenu(i);
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
    List<DrawerMenuModel> list = findAllOrdered();
    if (CollectionUtils.isEmpty(list)) {
      // only first time
      initDrawerMenus();
      list = findAllOrdered();
    }
    return list;
  }
  
  @Override
  public String[] findLabels() {
    return listToArray(new Function<DrawerMenuModel, String>() {
      @Override
      public String apply(DrawerMenuModel input) {
        return input.getLabel();
      }
    });
  }
  
  @Override
  public String[] findActionBarTitles() {
    return listToArray(new Function<DrawerMenuModel, String>() {
      @Override
      public String apply(DrawerMenuModel input) {
        return input.getActionBarTitle();
      }
    });
  }

  private List<DrawerMenuModel> findAllOrdered() {
    try {
      return dao.query(dao.queryBuilder().orderBy("orderMenu", true).prepare());
    } catch (SQLException e) {
      log.error("DrawerMenu#findAllOrdered", e);
    }
    return Lists.newArrayList();
  }
  
  private String[] listToArray(Function<DrawerMenuModel, String> function) {
    // TODO SELECT ?
    return FluentIterable.from(findAllOrdered()).transform(function).toArray(String.class);
  }
  
}
