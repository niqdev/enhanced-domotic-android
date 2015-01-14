package com.domotic.enhanced.activity;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.widget.CompoundButton;

import com.domotic.enhanced.R;
import com.domotic.enhanced.lib.Light;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

  private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

  // TODO orm
  // TODO navigation drawer

  @Bean
  Light light;

  @CheckedChange(R.id.switch_light)
  public void switchLight(boolean isChecked, CompoundButton button) {
    log.debug("switchLight: {}", isChecked);
    if (isChecked) {
      light.turnOnById(21);
    } else {
      light.turnOffById(21);
    }
  }

}
