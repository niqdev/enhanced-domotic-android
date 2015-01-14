package com.domotic.enhanced.lib;

public interface ILight {
  
  void turnOnAll();
  
  void turnOffAll();
  
  void turnOnById(Integer id);
  
  void turnOffById(Integer id);
  
  void turnOnGroup(Integer group);
  
  void turnOffGroup(Integer group);
  
  void turnOnEnvironment(Integer environment);
  
  void turnOffEnvironment(Integer environment);

}
