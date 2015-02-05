package com.domotic.enhanced.activity;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.model.LightModel;
import com.domotic.enhanced.repository.LightRepository;
import com.domotic.enhanced.repository.LightRepositoryImpl;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType;
import com.mobsandgeeks.saripaar.annotation.Required;

@EActivity(R.layout.edit_light)
public class EditLightActivity extends Activity {
  
  @Bean(LightRepositoryImpl.class)
  LightRepository repository;
  
  @ViewById(R.id.editText_editLightDeviceId)
  @Required(order = 1, messageResId = R.string.validation_required)
  @NumberRule(order = 2, type = NumberType.INTEGER, message = "TODO only number")
  EditText editTextDeviceId;
  
  @ViewById(R.id.editText_editLightName)
  @Required(order = 3, messageResId = R.string.validation_required)
  EditText editTextName;
  
  @ViewById(R.id.editText_editLightDescription)
  EditText editTextDescription;
  
  @AfterTextChange({
    R.id.editText_editLightDeviceId,
    R.id.editText_editLightName,
    R.id.editText_editLightDescription
  })
  void clearAllValidation(TextView textView) {
    textView.setError(null);
  }
  
  @Click(R.id.button_editLight)
  void addLight() {
    LightModel light = new LightModel();
    light.setDeviceId(Integer.valueOf(editTextDeviceId.getText().toString()));
    light.setName(editTextName.getText().toString());
    light.setDescription(StringUtils.trimToNull(editTextDescription.getText().toString()));
    repository.add(light);
  }

}
