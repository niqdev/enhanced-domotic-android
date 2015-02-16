package com.domotic.enhanced.fragment.dialog;

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
import com.domotic.enhanced.fragment.dialog.EditDialogFragment.EditListenerDialogFragment;

@EBean
public class EditDialogFragment<F extends EditListenerDialogFragment> extends DialogFragment {
  
  private static final Logger log = LoggerFactory.getLogger(EditDialogFragment.class);

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    return new AlertDialog.Builder(getActivity())
      .setItems(R.array.edit_dialog, new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
          dialog.dismiss();
          switch (which) {
          case 0:
            invokeMethod("edit");
            break;
          case 1:
            invokeMethod("delete");
            break;
          }
        }
      })
      .create();
  }
  
  @SuppressWarnings("unchecked")
  private void invokeMethod(String methodName) {
    try {
      MethodUtils.invokeMethod((F) getFragmentManager().findFragmentById(R.id.content_frame), methodName);
    } catch (Exception e) {
      log.error("fragment must implements EditListenerDialogFragment", e);
    }
  }
  
  /**
   * 
   */
  public interface EditListenerDialogFragment {
    
    void edit();

    void delete();
    
  }

}
