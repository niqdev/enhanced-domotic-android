package com.domotic.enhanced.preference;

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

  @Override
  public Protocol protocol() {
    return Protocol.stringToEnum(preference.protocol().get());
  }

  @Override
  public String host() {
    return preference.host().get();
  }

  @Override
  public Integer port() {
    return Integer.valueOf(preference.port().get());
  }

  @Override
  public Context context() {
    return context;
  }

}
