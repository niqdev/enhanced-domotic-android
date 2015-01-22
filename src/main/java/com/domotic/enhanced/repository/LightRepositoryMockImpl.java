package com.domotic.enhanced.repository;

import java.util.List;

import org.androidannotations.annotations.EBean;

import com.domotic.enhanced.model.Light;
import com.google.common.collect.Lists;

@EBean
public class LightRepositoryMockImpl implements LightRepository {

  @Override
  public List<Light> findAll() {
    return Lists.newArrayList(new Light(1, "luce1"), new Light(2, "luce2"), new Light(3, "luce3"));
  }

}
