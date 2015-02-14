package com.domotic.enhanced.fragment.light;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.lib.EnhancedLight;
import com.domotic.enhanced.lib.EnhancedLightImpl;
import com.domotic.enhanced.model.LightModel;

@EViewGroup(R.layout.item_light)
public class LightItemView extends LinearLayout {
  
  private static final Logger log = LoggerFactory.getLogger(LightItemView.class);
  
  @Bean(EnhancedLightImpl.class)
  EnhancedLight enhancedLight;
  
  @ViewById(R.id.switch_light)
  Switch switchLight;
  
  @ViewById(R.id.textView_lightDescription)
  TextView textDescription;

  public LightItemView(Context context) {
    super(context);
  }
  
  public void bind(LightModel light) {
    switchLight.setTag(light);
    switchLight.setText(light.getName());
    textDescription.setText(light.getDescription());
  }
  
  @CheckedChange(R.id.switch_light)
  public void switchLight(boolean isChecked, CompoundButton button) {
    Integer deviceId = ((LightModel) switchLight.getTag()).getDeviceId();
    log.debug("switchLight [{}] {}", deviceId, isChecked);
    if (isChecked) {
      enhancedLight.turnOnById(deviceId);
    } else {
      enhancedLight.turnOffById(deviceId);
    }
  }

}
