package com.domotic.enhanced.repository;

import java.util.List;

public interface CommonRepository<M, K> {
  
  List<M> findAll();
  
  void add(M model);
  
  void update(M model);
  
  void delete(K id);
  
  Long count();

}
