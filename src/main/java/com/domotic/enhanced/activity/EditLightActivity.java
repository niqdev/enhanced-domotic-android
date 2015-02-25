package com.domotic.enhanced.activity;

import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.apache.commons.lang3.StringUtils.trimToNull;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import android.widget.EditText;
import android.widget.TextView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.model.LightModel;
import com.domotic.enhanced.repository.LightRepository;
import com.domotic.enhanced.repository.impl.LightRepositoryImpl;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType;
import com.mobsandgeeks.saripaar.annotation.Required;

@EActivity(R.layout.edit_light)
public class EditLightActivity extends AbstractValidationActivity<LightModel, Long> {
  
  @Extra
  LightModel model;
  
  @Bean(LightRepositoryImpl.class)
  LightRepository repository;
  
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
  void clearValidation(TextView textView) {
    super.clearAllValidation(textView);
  }

  @Override
  void initAddViews() {
    // ready
  }

  @Override
  void initEditViews() {
    setButtonTag(model.getId());
    
    editTextDeviceId.setText(model.getDeviceId().toString());
    editTextName.setText(model.getName());
    editTextDescription.setText(
      "-".equals(StringUtils.trimToEmpty(model.getDescription())) ? "" : model.getDescription());
  }

  @Override
  void onAddValidationSucceeded() {
    repository.add(parseLight());
  }

  @Override
  void onEditValidationSucceeded() {
    repository.update(parseLight());
  }
  
  private LightModel parseLight() {
    LightModel light = new LightModel();
    light.setId(getButtonTag());
    light.setDeviceId(Integer.valueOf(editTextDeviceId.getText().toString()));
    light.setName(editTextName.getText().toString());
    light.setDescription(defaultString(trimToNull(editTextDescription.getText().toString()), "-"));
    return light;
  }
  
}
