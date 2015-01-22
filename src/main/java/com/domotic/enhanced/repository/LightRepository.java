package com.domotic.enhanced.repository;

import java.util.List;

import com.domotic.enhanced.model.Light;

public interface LightRepository {
  
  List<Light> findAll();

}
