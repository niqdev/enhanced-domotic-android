package com.domotic.enhanced.repository;

import java.util.List;

import org.androidannotations.annotations.EBean;

import com.domotic.enhanced.model.Light;
import com.google.common.collect.Lists;

@EBean
public class LightRepositoryMockImpl implements LightRepository {

  @Override
  public List<Light> findAll() {
    Light light1 = new Light();
    light1.setId(1L);
    light1.setDeviceId(21);
    light1.setName("name1");
    light1.setDescription("description1");
    
    Light light2 = new Light();
    light2.setId(2L);
    light2.setDeviceId(22);
    light2.setName("name2");
    light2.setDescription("description2");
    
    Light light3 = new Light();
    light3.setId(3L);
    light3.setDeviceId(23);
    light3.setName("name3");
    light3.setDescription("description3");
    
    return Lists.newArrayList(light1, light2, light3);
  }

}
