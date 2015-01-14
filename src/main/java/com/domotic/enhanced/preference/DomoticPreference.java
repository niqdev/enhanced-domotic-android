package com.domotic.enhanced.preference;

import static org.androidannotations.annotations.sharedpreferences.SharedPref.Scope.UNIQUE;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(UNIQUE)
public interface DomoticPreference {
  
  @DefaultString("0.0.0.0")
  String host();
  
  @DefaultInt(20000)
  int port();
  
}
