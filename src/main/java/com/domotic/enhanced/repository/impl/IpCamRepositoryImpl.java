package com.domotic.enhanced.repository.impl;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

import com.domotic.enhanced.database.DatabaseHelper;
import com.domotic.enhanced.model.IpCamModel;
import com.domotic.enhanced.repository.IpCamRepository;
import com.j256.ormlite.dao.RuntimeExceptionDao;

@EBean
public class IpCamRepositoryImpl
  extends AbstractCommonRepository<IpCamModel, Long> implements IpCamRepository {
  
  @OrmLiteDao(helper = DatabaseHelper.class)
  RuntimeExceptionDao<IpCamModel, Long> dao;

  @Override
  protected RuntimeExceptionDao<IpCamModel, Long> getDao() {
    return dao;
  }

}
