package com.domotic.enhanced.fragment.light;

import static com.domotic.enhanced.activity.ActivityIntentType.ADD;
import static com.domotic.enhanced.activity.ActivityIntentType.EDIT;
import static com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment.PARAM_MESSAGE;
import static com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment.PARAM_TITLE;
import static com.domotic.enhanced.fragment.dialog.EditDialogFragment.PARAM_MODEL;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.domotic.enhanced.R;
import com.domotic.enhanced.activity.EditLightActivity_;
import com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment;
import com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment.ConfirmListenerDialogFragment;
import com.domotic.enhanced.fragment.dialog.EditDialogFragment;
import com.domotic.enhanced.fragment.dialog.EditDialogFragment.EditListenerDialogFragment;
import com.domotic.enhanced.model.LightModel;

@EFragment(R.layout.fragment_light)
@OptionsMenu(R.menu.menu_light)
public class LightFragment extends Fragment
  implements EditListenerDialogFragment, ConfirmListenerDialogFragment {
  
  @ViewById(R.id.list_light)
  ListView list;
  
  @Bean(LightListAdapter.class)
  BaseAdapter adapter;
  
  @Bean(EditDialogFragment.class)
  EditDialogFragment<LightModel, LightFragment> editDialog;
  
  @Bean(ConfirmDialogFragment.class)
  ConfirmDialogFragment<LightFragment> confirmDialog;
  
  @StringRes(R.string.dialog_delete_title_delete)
  String dialogTitleDelete;
  
  @StringRes(R.string.dialog_delete_message_confirm)
  String dialogMessageConfirm;

  @AfterViews
  void bindAdapter() {
    list.setAdapter(adapter);
  }
  
  @OptionsItem(R.id.action_light_add)
  void addLight() {
    EditLightActivity_.intent(this).intentType(ADD).start();
  }

  @Override
  public void onResume() {
    super.onResume();
    adapter.notifyDataSetChanged();
  }
  
  @ItemLongClick(R.id.list_light)
  void onLongClick(LightModel light) {
    Bundle params = new Bundle();
    params.putSerializable(PARAM_MODEL, light);
    editDialog.setArguments(params);
    editDialog.show(getFragmentManager(), getClass().getSimpleName());
  }
  
  /* Dialogs */

  @Override
  public <M> void editDialog(M model) {
    EditLightActivity_.intent(this).intentType(EDIT).model((LightModel) model).start();
  }

  @Override
  public <M> void deleteDialog(M model) {
    Bundle params = new Bundle();
    params.putString(PARAM_TITLE, dialogTitleDelete);
    params.putString(PARAM_MESSAGE, dialogMessageConfirm);
    confirmDialog.setArguments(params);
    confirmDialog.show(getFragmentManager(), getClass().getSimpleName());
  }

  @Override
  public void confirmDialog() {
    // TODO
    Toast.makeText(getActivity(), "DELETE", Toast.LENGTH_SHORT).show();
  }
  
}
