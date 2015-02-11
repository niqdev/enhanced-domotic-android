package com.domotic.enhanced.repository.impl;

import java.util.List;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

import com.domotic.enhanced.database.DatabaseHelper;
import com.domotic.enhanced.model.LightModel;
import com.domotic.enhanced.repository.LightRepository;
import com.j256.ormlite.dao.RuntimeExceptionDao;

@EBean
public class LightRepositoryImpl implements LightRepository {
  
  @OrmLiteDao(helper = DatabaseHelper.class)
  RuntimeExceptionDao<LightModel, Long> dao;

  @Override
  public List<LightModel> findAll() {
    return dao.queryForAll();
  }

  @Override
  public void add(LightModel light) {
    dao.create(light);
  }

}
