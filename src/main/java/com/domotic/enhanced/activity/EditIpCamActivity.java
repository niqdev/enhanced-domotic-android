package com.domotic.enhanced.activity;

import android.app.Activity;
import android.view.View;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;

public class EditIpCamActivity extends Activity implements ValidationListener {

  @Override
  public void onValidationSucceeded() {
    throw new UnsupportedOperationException("not implemented yet");
  }

  @Override
  public void onValidationFailed(View failedView, Rule<?> failedRule) {
    throw new UnsupportedOperationException("not implemented yet");
  }

}
