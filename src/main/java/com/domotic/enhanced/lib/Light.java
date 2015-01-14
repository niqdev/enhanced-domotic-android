package com.domotic.enhanced.lib;

import static com.domotic.enhanced.domain.EAction.ActionType.TURN_OFF;
import static com.domotic.enhanced.domain.EAction.ActionType.TURN_ON;
import static com.domotic.enhanced.domain.EDevice.DeviceType.LIGHT;
import static com.domotic.enhanced.domain.EDeviceProperty.DevicePropertyType.ALL;
import static com.domotic.enhanced.domain.EDeviceProperty.DevicePropertyType.ENVIRONMENT;
import static com.domotic.enhanced.domain.EDeviceProperty.DevicePropertyType.GROUP;
import static com.domotic.enhanced.domain.EDeviceProperty.DevicePropertyType.ID;
import static com.domotic.enhanced.domain.EDomotic.SyntaxType.COMMAND;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import com.domotic.enhanced.EnhancedDomotic;
import com.domotic.enhanced.preference.DomoticConfig;

@EBean
public class Light implements ILight {
  
  @Bean
  DomoticConfig config;
  
  @Bean
  DialogHandler handler;
  
  EnhancedDomotic<?> domotic;
  
  @AfterInject
  void init() {
    // TODO verify if really singleton
    domotic = EnhancedDomotic.config(config);
  }
  
  private EnhancedDomotic<?> command() {
    return domotic.type(COMMAND).handler(handler);
  }
  
  @Override
  public void turnOnAll() {
    command().action(TURN_ON).device(LIGHT).deviceProperty(ALL).execute();
  }

  @Override
  public void turnOffAll() {
    command().action(TURN_OFF).device(LIGHT).deviceProperty(ALL).execute();
  }

  @Override
  public void turnOnById(Integer id) {
    command().action(TURN_ON).device(LIGHT).deviceProperty(ID, id).execute();
  }

  @Override
  public void turnOffById(Integer id) {
    command().action(TURN_OFF).device(LIGHT).deviceProperty(ID, id).execute();
  }

  @Override
  public void turnOnGroup(Integer group) {
    command().action(TURN_ON).device(LIGHT).deviceProperty(GROUP, group).execute();
  }

  @Override
  public void turnOffGroup(Integer group) {
    command().action(TURN_OFF).device(LIGHT).deviceProperty(GROUP, group).execute();
  }

  @Override
  public void turnOnEnvironment(Integer environment) {
    command().action(TURN_ON).device(LIGHT).deviceProperty(ENVIRONMENT, environment).execute();
  }

  @Override
  public void turnOffEnvironment(Integer environment) {
    command().action(TURN_OFF).device(LIGHT).deviceProperty(ENVIRONMENT, environment).execute();
  }

}
