package com.domotic.enhanced.fragment.dialog;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import com.domotic.enhanced.R;
import com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.DialogFragmentListener;

public abstract class AbstractDialogFragment<V extends Serializable, L extends DialogFragmentListener<V>> extends DialogFragment {
  
  private static final Logger log = LoggerFactory.getLogger(AbstractDialogFragment.class);
  
  public static final String PARAM_TITLE = "com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_TITLE";
  public static final String PARAM_MESSAGE = "com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_MESSAGE";
  public static final String PARAM_VALUE = "com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_VALUE";
  
  @SuppressWarnings("unchecked")
  protected void invokeListenerMethodSingleParam(String methodName) {
    try {
      MethodUtils.invokeMethod(
        (L) getFragmentManager().findFragmentById(getFragmentId()), methodName, getArgValue());
    } catch (Exception e) {
      log.error("fragment must implements an interface that extends DialogFragmentListener with single serializable parameter methods", e);
    }
  }
  
  @Override
  public final Dialog onCreateDialog(Bundle savedInstanceState) {
    return createDialog(savedInstanceState);
  }

  public abstract Dialog createDialog(Bundle savedInstanceState);
  
  @SuppressWarnings("unchecked")
  protected V getArgValue() {
    return checkNotNull((V) getArguments().getSerializable(PARAM_VALUE));
  }
  
  protected int getFragmentId() {
    return R.id.content_frame;
  }
  
  /**
   * 
   */
  public interface DialogFragmentListener<V extends Serializable> {
    
  }

}
