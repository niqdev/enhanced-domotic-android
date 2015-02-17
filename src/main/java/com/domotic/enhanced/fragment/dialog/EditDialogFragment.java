package com.domotic.enhanced.fragment.dialog;

import java.io.Serializable;

import org.androidannotations.annotations.EBean;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.domotic.enhanced.R;
import com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.DialogFragmentListener;
import com.domotic.enhanced.fragment.dialog.EditDialogFragment.EditDialogFragmentListener;

@EBean
public class EditDialogFragment<V extends Serializable> extends AbstractDialogFragment<V, EditDialogFragmentListener<V>> {
  
  @Override
  public Dialog createDialog(Bundle savedInstanceState) {
    return new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK)
      .setItems(R.array.edit_dialog, new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
          dialog.dismiss();
          switch (which) {
          case 0:
            invokeListenerMethodSingleParam("editDialog");
            break;
          case 1:
            invokeListenerMethodSingleParam("deleteDialog");
            break;
          }
        }
      })
      .create();
  }
  
  /**
   * 
   */
  public interface EditDialogFragmentListener<V extends Serializable> extends DialogFragmentListener<V> {
    
    void editDialog(V value);

    void deleteDialog(V value);
    
  }

}
