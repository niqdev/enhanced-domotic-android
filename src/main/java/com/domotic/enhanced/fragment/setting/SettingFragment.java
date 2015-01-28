package com.domotic.enhanced.fragment.setting;

import static com.domotic.enhanced.Config.Validator.isValidHost;
import static com.domotic.enhanced.Config.Validator.isValidPort;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.res.StringRes;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.widget.Toast;

import com.domotic.enhanced.R;
import com.domotic.enhanced.preference.DomoticPreference;

@EFragment
public class SettingFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {
  
  @StringRes(R.string.preference_key_host)
  String preferenceKeyHost;
  
  @StringRes(R.string.preference_key_port)
  String preferenceKeyPort;
  
  @StringRes(R.string.preference_bad_value)
  String errorBadValue;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getPreferenceManager().setSharedPreferencesName(DomoticPreference.class.getSimpleName());
    addPreferencesFromResource(R.xml.setting_main);
    validatePreferences();
    updatePreferenceSummary(getPreferenceScreen());
  }
  
  @Override
  public void onStart() {
    super.onStart();
    getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
  }

  @Override
  public void onStop() {
    super.onStop();
    getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
  }
  
  @Override
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    updatePreferenceSummary(findPreference(key));
  }
  
  private void updatePreferenceSummary(Preference preference) {
    if (preference instanceof PreferenceGroup) {
      PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
      for (int i=0; i<preferenceGroup.getPreferenceCount(); i++) {
        // recursive
        updatePreferenceSummary(preferenceGroup.getPreference(i));
      }
    } else if (preference instanceof EditTextPreference) {
      EditTextPreference editTextPreference = (EditTextPreference) preference;
      preference.setSummary(editTextPreference.getText());
    } else if (preference instanceof ListPreference) {
      ListPreference listPreference = (ListPreference) preference;
      preference.setSummary(listPreference.getEntry());
    }
  }
  
  private void validatePreferences() {
    findPreference(preferenceKeyHost).setOnPreferenceChangeListener(validate(new HostPreferenceValidation()));
    findPreference(preferenceKeyPort).setOnPreferenceChangeListener(validate(new PortPreferenceValidation()));
  }
  
  private <T> Preference.OnPreferenceChangeListener validate(final PreferenceValidation validation) {
    return new Preference.OnPreferenceChangeListener() {
      
      @Override
      public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean isValid = validation.isValid(newValue);
        if (!isValid) {
          Toast.makeText(getActivity(), errorBadValue, Toast.LENGTH_LONG).show();
        }        
        return isValid;
      }
    };
  }
  
  /**
   * 
   */
  interface PreferenceValidation {
    
    boolean isValid(Object value);
    
  }
  
  private class HostPreferenceValidation implements PreferenceValidation {

    @Override
    public boolean isValid(Object value) {
      String host = trimToEmpty((String) value);
      return isNotBlank(host) && isValidHost(host);
    }
  }
  
  private class PortPreferenceValidation implements PreferenceValidation {

    @Override
    public boolean isValid(Object value) {
      String port = trimToEmpty((String) value);
      return isNumeric(port) && isValidPort(Integer.valueOf(port));
    }
  }
  
}
