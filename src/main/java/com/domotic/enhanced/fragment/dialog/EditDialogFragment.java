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
import com.domotic.enhanced.fragment.dialog.EditDialogFragment.EditListenerDialogFragment;

@EBean
public class EditDialogFragment<M, F extends EditListenerDialogFragment> extends DialogFragment {
  
  public static final String PARAM_MODEL = "com.domotic.enhanced.fragment.dialog.EditDialogFragment.PARAM_MODEL";

  private static final Logger log = LoggerFactory.getLogger(EditDialogFragment.class);
  
  @SuppressWarnings("unchecked")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    final M model = (M) getArguments().getSerializable(PARAM_MODEL);
    checkNotNull(model);
    
    return new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK)
      .setItems(R.array.edit_dialog, new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
          dialog.dismiss();
          switch (which) {
          case 0:
            invokeMethod("editDialog", model);
            break;
          case 1:
            invokeMethod("deleteDialog", model);
            break;
          }
        }
      })
      .create();
  }
  
  @SuppressWarnings("unchecked")
  private void invokeMethod(String methodName, M model) {
    try {
      MethodUtils.invokeMethod(
        (F) getFragmentManager().findFragmentById(R.id.content_frame), methodName, model);
    } catch (Exception e) {
      log.error("fragment must implements EditListenerDialogFragment", e);
    }
  }
  
  /**
   * 
   */
  public interface EditListenerDialogFragment {
    
    <M> void editDialog(M model);

    <M> void deleteDialog(M model);
    
  }

}
