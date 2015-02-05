package com.domotic.enhanced.repository;

import java.util.List;

import com.domotic.enhanced.model.LightModel;

public interface LightRepository {
  
  List<LightModel> findAll();
  
  void add(LightModel light);

}
