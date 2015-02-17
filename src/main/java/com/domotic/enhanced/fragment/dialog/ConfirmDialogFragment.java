package com.domotic.enhanced.fragment.dialog;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import org.androidannotations.annotations.EBean;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.DialogFragmentListener;
import com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment.ConfirmDialogFragmentListener;

@EBean
public class ConfirmDialogFragment<V extends Serializable> extends AbstractDialogFragment<V, ConfirmDialogFragmentListener<V>> {
  
  @Override
  public Dialog createDialog(Bundle savedInstanceState) {
    String title = getArguments().getString(PARAM_TITLE);
    String message = getArguments().getString(PARAM_MESSAGE);
    return new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK)
      .setIcon(android.R.drawable.ic_dialog_info)
      .setTitle(checkNotNull(title))
      .setMessage(checkNotNull(message))
      .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
          invokeListenerMethodSingleParam("confirmDialog");
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
  
  /**
   * 
   */
  public interface ConfirmDialogFragmentListener<V extends Serializable> extends DialogFragmentListener<V> {
    
    void confirmDialog(V value);
    
  }
  
}
