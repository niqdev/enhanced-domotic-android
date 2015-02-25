package com.domotic.enhanced.repository.impl;

import java.util.List;

import org.androidannotations.annotations.EBean;

import com.domotic.enhanced.repository.CommonRepository;
import com.j256.ormlite.dao.RuntimeExceptionDao;

@EBean
public abstract class AbstractCommonRepository<M, K> implements CommonRepository<M, K> {
  
  // AndroidAnnotations issue
  protected abstract RuntimeExceptionDao<M, K> getDao();
  
  @Override
  public List<M> findAll() {
    return getDao().queryForAll();
  }

  @Override
  public void add(M model) {
    getDao().create(model);
  }

  @Override
  public void update(M model) {
    getDao().update(model);
  }

  @Override
  public void delete(K id) {
    getDao().deleteById(id);
  }

  @Override
  public Long count() {
    return getDao().countOf();
  }
  
}
