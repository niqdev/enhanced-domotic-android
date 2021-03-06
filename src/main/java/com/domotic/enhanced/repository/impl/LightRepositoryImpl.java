package com.domotic.enhanced.repository.impl;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

import com.domotic.enhanced.database.DatabaseHelper;
import com.domotic.enhanced.model.LightModel;
import com.domotic.enhanced.repository.LightRepository;
import com.j256.ormlite.dao.RuntimeExceptionDao;

@EBean
public class LightRepositoryImpl
  extends AbstractCommonRepository<LightModel, Long> implements LightRepository {
  
  @OrmLiteDao(helper = DatabaseHelper.class)
  RuntimeExceptionDao<LightModel, Long> dao;

  @Override
  protected RuntimeExceptionDao<LightModel, Long> getDao() {
    return dao;
  }

}
