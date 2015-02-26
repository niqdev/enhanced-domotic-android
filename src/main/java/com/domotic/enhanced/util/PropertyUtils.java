package com.domotic.enhanced.util;

import java.io.IOException;
import java.util.Properties;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.content.res.AssetManager;

@EBean
public class PropertyUtils {
  
  @RootContext
  Context context;
  
  private static final Logger log = LoggerFactory.getLogger(PropertyUtils.class);
  
  private static final String FILE_NAME = "config.properties";
  
  public static final String PROP_MOCK_IPCAM = "mock.ipcam";
  
  private String readProperty(String name) {
    AssetManager assets = context.getResources().getAssets();
    Properties properties = new Properties();
    try {
      properties.load(assets.open(FILE_NAME));
      return properties.getProperty(name);
    } catch (IOException e) {
      log.error("error while opening property file: ", e);
    }
    return null;
  }
  
  public boolean readBoolean(String name) {
    String property = readProperty(name);
    return property != null ? Boolean.valueOf(property) : false;
  }

}
