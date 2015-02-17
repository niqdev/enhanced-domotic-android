package com.domotic.enhanced.fragment.light;

import static com.domotic.enhanced.activity.ActivityIntentType.ADD;
import static com.domotic.enhanced.activity.ActivityIntentType.EDIT;
import static com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_MESSAGE;
import static com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_TITLE;
import static com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_VALUE;

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

import com.domotic.enhanced.R;
import com.domotic.enhanced.activity.EditLightActivity_;
import com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment;
import com.domotic.enhanced.fragment.dialog.ConfirmDialogFragment.ConfirmDialogFragmentListener;
import com.domotic.enhanced.fragment.dialog.EditDialogFragment;
import com.domotic.enhanced.fragment.dialog.EditDialogFragment.EditDialogFragmentListener;
import com.domotic.enhanced.model.LightModel;
import com.domotic.enhanced.repository.LightRepository;
import com.domotic.enhanced.repository.impl.LightRepositoryImpl;

@EFragment(R.layout.fragment_light)
@OptionsMenu(R.menu.menu_light)
public class LightFragment extends Fragment
  implements EditDialogFragmentListener<LightModel>, ConfirmDialogFragmentListener<LightModel> {
  
  @ViewById(R.id.list_light)
  ListView list;
  
  @Bean(LightListAdapter.class)
  BaseAdapter adapter;
  
  @Bean(LightRepositoryImpl.class)
  LightRepository repository;
  
  @Bean(EditDialogFragment.class)
  EditDialogFragment<LightModel> editDialog;
  
  @Bean(ConfirmDialogFragment.class)
  ConfirmDialogFragment<LightModel> confirmDialog;
  
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
    params.putSerializable(PARAM_VALUE, light);
    editDialog.setArguments(params);
    editDialog.show(getFragmentManager(), getClass().getSimpleName());
  }
  
  /* Dialogs */

  @Override
  public void editDialog(LightModel value) {
    EditLightActivity_.intent(this).intentType(EDIT).model(value).start();
  }

  @Override
  public void deleteDialog(LightModel value) {
    Bundle params = new Bundle();
    params.putString(PARAM_TITLE, dialogTitleDelete);
    params.putString(PARAM_MESSAGE, dialogMessageConfirm);
    params.putSerializable(PARAM_VALUE, value);
    confirmDialog.setArguments(params);
    confirmDialog.show(getFragmentManager(), getClass().getSimpleName());
  }
  
  @Override
  public void confirmDialog(LightModel value) {
    repository.delete(value.getId());
    adapter.notifyDataSetChanged();
  }
  
}
