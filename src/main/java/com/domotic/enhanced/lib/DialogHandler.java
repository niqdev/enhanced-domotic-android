package com.domotic.enhanced.lib;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.res.StringRes;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.domotic.enhanced.R;
import com.domotic.enhanced.client.LogHandler;
import com.domotic.enhanced.client.Request;
import com.domotic.enhanced.client.Response;

@EBean
@SuppressWarnings({"rawtypes", "unchecked"})
public class DialogHandler extends LogHandler {
  
  @RootContext
  Context context;
  
  @StringRes(R.string.progress_dialog_load)
  String msgProgressLoading;
  
  @StringRes(R.string.connection_error)
  String msgConnectionError;

  private ProgressDialog progressDialog;
  
  @Override
  public void onValidation(Request request) {
    super.onValidation(request);
    showLoading(msgProgressLoading);
  }

  @Override
  public void onSuccess(Request request, Response response) {
    super.onSuccess(request, response);
    hideLoading();
  }

  @Override
  public void onError(Exception e) {
    super.onError(e);
    hideLoading();
    showError(msgConnectionError);
  }

  @UiThread
  void showLoading(String message) {
    progressDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_DARK);
    progressDialog.setIndeterminate(true);
    progressDialog.setCancelable(false);
    progressDialog.setMessage(message);
    progressDialog.show();
  }
  
  @UiThread
  void hideLoading() {
    if (progressDialog != null) {
      progressDialog.dismiss();
    }
  }
  
  @UiThread
  void showError(String message) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }

}
