package com.domotic.enhanced.util;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Rule;

@EBean
public class ValidationUtils {
  
  @RootContext
  Context context;
  
  public void onValidationFailed(View failedView, Rule<?> failedRule) {
    String message = failedRule.getFailureMessage();
    if (failedView instanceof EditText) {
      failedView.requestFocus();
      ((EditText) failedView).setError(message);
    } else {
      Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
  }

}
