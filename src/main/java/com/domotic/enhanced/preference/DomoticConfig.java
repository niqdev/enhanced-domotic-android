package com.domotic.enhanced.preference;

import static com.domotic.enhanced.domain.Protocol.OPENWEBNET;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.content.Context;

import com.domotic.enhanced.Config;
import com.domotic.enhanced.domain.Protocol;

@EBean
public class DomoticConfig implements Config {
  
  @RootContext
  Context context;
  
  @Pref
  DomoticPreference_ preference;
  
  public void initHost(String host) {
    preference.edit().host().put(host).apply();
  }
  
  public void initPort(Integer port) {
    preference.edit().port().put(port).apply();
  }

  @Override
  public Protocol protocol() {
    // TODO preference
    return OPENWEBNET;
  }

  @Override
  public String host() {
    //return preference.host().get();
    return "192.168.1.41";
  }

  @Override
  public Integer port() {
    return preference.port().get();
  }

  @Override
  public Context context() {
    return context;
  }

}
