package com.domotic.enhanced.activity;

import org.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.view.View;

import com.domotic.enhanced.R;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;

@EActivity(R.layout.edit_ipcam)
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
