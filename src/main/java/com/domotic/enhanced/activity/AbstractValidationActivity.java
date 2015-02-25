package com.domotic.enhanced.activity;

import java.io.Serializable;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.util.ValidationUtils;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;

@EActivity
public abstract class AbstractValidationActivity<M extends Serializable, T>
  extends Activity implements ValidationListener {
  
  @Extra
  ActivityIntentType intentType;
  
  @StringRes(R.string.label_add)
  String labelAdd;
  
  @StringRes(R.string.label_edit)
  String labelEdit;
  
  @ViewById(R.id.button_editCommon)
  Button buttonEditCommon;
  
  @Bean
  ValidationUtils validationUtils;
  
  private Validator validator;
  
  @OptionsItem(android.R.id.home)
  void onBackAction() {
    // action back button same behaviour
    this.onBackPressed();
  }
  
  @AfterViews
  void initViews() {
    switch (intentType) {
    case ADD:
      buttonEditCommon.setText(labelAdd);
      initAddViews();
      break;
    case EDIT:
      buttonEditCommon.setText(labelEdit);
      initEditViews();
      break;
    }
  }
  
  void clearAllValidation(TextView textView) {
    textView.setError(null);
  }
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    validator = new Validator(this);
    validator.setValidationListener(this);
    getActionBar().setDisplayHomeAsUpEnabled(true);
  }
  
  @Click(R.id.button_editCommon)
  void buttonEditCommon() {
    validator.validate();
  }

  @Override
  public void onValidationSucceeded() {
    switch (intentType) {
    case ADD:
      onAddValidationSucceeded();
      break;
    case EDIT:
      onEditValidationSucceeded();
      break;
    }
    finish();
  }
  
  @Override
  public void onValidationFailed(View failedView, Rule<?> failedRule) {
    validationUtils.onValidationFailed(failedView, failedRule);
  }
  
  protected void setButtonTag(T tag) {
    buttonEditCommon.setTag(tag);
  }
  
  @SuppressWarnings("unchecked")
  protected T getButtonTag() {
    return (T) buttonEditCommon.getTag();
  }
  
  /* abstract */
  
  abstract void initAddViews();
  
  abstract void initEditViews();
  
  abstract void onAddValidationSucceeded();
  
  abstract void onEditValidationSucceeded();

}
