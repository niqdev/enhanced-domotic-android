package com.domotic.enhanced.fragment;

import static com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_MESSAGE;
import static com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_TITLE;
import static com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_VALUE;

import java.io.Serializable;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment;
import com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment.ConfirmDialogFragmentListener;
import com.domotic.enhanced.fragment.dialog.EditDialogFragment;
import com.domotic.enhanced.fragment.dialog.EditDialogFragment.EditDialogFragmentListener;

@EBean
public abstract class AbstractFragmentList<V extends Serializable> extends Fragment
  implements EditDialogFragmentListener<V>, ConfirmDialogFragmentListener<V> {
  
  @StringRes(R.string.dialog_delete_title_delete)
  protected String dialogTitleDelete;
  
  @StringRes(R.string.dialog_delete_message_confirm)
  protected String dialogMessageConfirm;
  
  @ViewById(R.id.list_fragment)
  protected ListView list;
  
  @ViewById(android.R.id.empty)
  protected ViewStub listEmpty;
  
  @Bean(EditDialogFragment.class)
  protected EditDialogFragment<V> editDialog;
  
  @Bean(ConfirmDialogFragment.class)
  protected ConfirmDialogFragment<V> confirmDialog;
  
  protected abstract BaseAdapter getBaseAdapter();
  
  @AfterViews
  protected void bindAdapter() {
    list.setAdapter(getBaseAdapter());
    list.setEmptyView(listEmpty);
  }
  
  @Override
  public void onResume() {
    super.onResume();
    getBaseAdapter().notifyDataSetChanged();
  }
  
  protected void showEditDialog(Bundle params) {
    editDialog.setArguments(params);
    editDialog.show(getFragmentManager(), getClass().getSimpleName());
  }
  
  /* Dialogs */

  @Override
  public void deleteDialog(V value) {
    Bundle params = new Bundle();
    params.putString(PARAM_TITLE, dialogTitleDelete);
    params.putString(PARAM_MESSAGE, dialogMessageConfirm);
    params.putSerializable(PARAM_VALUE, value);
    confirmDialog.setArguments(params);
    confirmDialog.show(getFragmentManager(), getClass().getSimpleName());
  }
  
}
