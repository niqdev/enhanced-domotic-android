package com.domotic.enhanced.fragment.dialog;

import static com.google.common.base.Preconditions.checkNotNull;

import org.androidannotations.annotations.EBean;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.domotic.enhanced.R;
import com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment.ConfirmListenerDialogFragment;

@EBean
public class ConfirmDialogFragment<F extends ConfirmListenerDialogFragment> extends DialogFragment {
  
  public static final String PARAM_TITLE = "com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment.PARAM_TITLE";
  public static final String PARAM_MESSAGE = "com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment.PARAM_MESSAGE";

  private static final Logger log = LoggerFactory.getLogger(ConfirmDialogFragment.class);
  
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    String title = getArguments().getString(PARAM_TITLE);
    String message = getArguments().getString(PARAM_MESSAGE);
    
    return new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK)
      .setIcon(android.R.drawable.ic_dialog_info)
      .setTitle(checkNotNull(title))
      .setMessage(checkNotNull(message))
      .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
          invokeConfirmMethod();
        }
      })
      .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
          dialog.dismiss();
        }
      })
      .create();
  }
  
  @SuppressWarnings("unchecked")
  private void invokeConfirmMethod() {
    try {
      MethodUtils.invokeMethod((F) getFragmentManager().findFragmentById(R.id.content_frame), "confirmDialog");
    } catch (Exception e) {
      log.error("fragment must implements ConfirmListenerDialogFragment", e);
    }
  }
  
  /**
   * 
   */
  public interface ConfirmListenerDialogFragment {
    
    void confirmDialog();
    
  }
  
}
