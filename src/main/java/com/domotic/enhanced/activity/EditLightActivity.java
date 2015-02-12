package com.domotic.enhanced.activity;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.model.LightModel;
import com.domotic.enhanced.repository.LightRepository;
import com.domotic.enhanced.repository.impl.LightRepositoryImpl;
import com.domotic.enhanced.util.ValidationUtils;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType;
import com.mobsandgeeks.saripaar.annotation.Required;

@EActivity(R.layout.edit_light)
public class EditLightActivity extends Activity implements ValidationListener {
  
  // TODO
  public static final String NEW = "com.domotic.enhanced.activity.EditLightActivity.NEW";
  public static final String EDIT = "com.domotic.enhanced.activity.EditLightActivity.EDIT";
  
  @Bean(LightRepositoryImpl.class)
  LightRepository repository;
  
  @Bean
  ValidationUtils validationUtils;
  
  @ViewById(R.id.editText_editLightDeviceId)
  @Required(order = 1, messageResId = R.string.validation_required)
  // TODO remove openwebnet dependency
  @NumberRule(order = 2, type = NumberType.INTEGER, gt = 10, lt = 100, messageResId = R.string.validation_light)
  EditText editTextDeviceId;
  
  @ViewById(R.id.editText_editLightName)
  @Required(order = 3, messageResId = R.string.validation_required)
  EditText editTextName;
  
  @ViewById(R.id.editText_editLightDescription)
  EditText editTextDescription;
  
  @AfterTextChange({
    R.id.editText_editLightDeviceId,
    R.id.editText_editLightName
  })
  void clearAllValidation(TextView textView) {
    textView.setError(null);
  }
  
  private Validator validator;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    validator = new Validator(this);
    validator.setValidationListener(this);
  }

  @Click(R.id.button_editLight)
  void buttonLight() {
    validator.validate();
  }

  @Override
  public void onValidationSucceeded() {
    addLight();
    finish();
  }
  
  private void addLight() {
    LightModel light = new LightModel();
    light.setDeviceId(Integer.valueOf(editTextDeviceId.getText().toString()));
    light.setName(editTextName.getText().toString());
    light.setDescription(StringUtils.trimToNull(editTextDescription.getText().toString()));
    repository.add(light);
  }

  @Override
  public void onValidationFailed(View failedView, Rule<?> failedRule) {
    validationUtils.onValidationFailed(failedView, failedRule);
  }

}
