package com.domotic.enhanced.repository;

import java.util.List;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

import com.domotic.enhanced.database.DatabaseHelper;
import com.domotic.enhanced.model.Light;
import com.j256.ormlite.dao.RuntimeExceptionDao;

@EBean
public class LightRepositoryImpl implements LightRepository {
  
  @OrmLiteDao(helper = DatabaseHelper.class)
  RuntimeExceptionDao<Light, Long> dao;

  @Override
  public List<Light> findAll() {
    return dao.queryForAll();
  }

}
