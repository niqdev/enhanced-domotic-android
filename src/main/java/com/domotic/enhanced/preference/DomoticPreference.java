package com.domotic.enhanced.preference;

import static org.androidannotations.annotations.sharedpreferences.SharedPref.Scope.UNIQUE;

import org.androidannotations.annotations.sharedpreferences.DefaultRes;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

import com.domotic.enhanced.R;

@SharedPref(UNIQUE)
public interface DomoticPreference {
  
  @DefaultRes(value = R.string.preference_default_protocol, keyRes = R.string.preference_key_protocol)
  String protocol();
  
  @DefaultRes(value = R.string.preference_default_host, keyRes = R.string.preference_key_host)
  String host();
  
  @DefaultRes(value = R.string.preference_default_port, keyRes = R.string.preference_key_port)
  String port();
  
}
