package com.domotic.enhanced.fragment.light;

import static com.domotic.enhanced.activity.ActivityIntentType.ADD;
import static com.domotic.enhanced.activity.ActivityIntentType.EDIT;
import static com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_VALUE;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import android.os.Bundle;
import android.widget.BaseAdapter;

import com.domotic.enhanced.R;
import com.domotic.enhanced.activity.EditLightActivity_;
import com.domotic.enhanced.fragment.AbstractFragmentList;
import com.domotic.enhanced.model.LightModel;
import com.domotic.enhanced.repository.LightRepository;
import com.domotic.enhanced.repository.impl.LightRepositoryImpl;

@EFragment(R.layout.fragment_list)
@OptionsMenu(R.menu.menu_light)
public class LightFragment extends AbstractFragmentList<LightModel> {
  
  @Bean(LightListAdapter.class)
  BaseAdapter adapter;
  
  @Bean(LightRepositoryImpl.class)
  LightRepository repository;
  
  @Override
  public BaseAdapter getBaseAdapter() {
    return adapter;
  }
  
  @OptionsItem(R.id.action_light_add)
  void addLight() {
    EditLightActivity_.intent(this).intentType(ADD).start();
  }

  @ItemLongClick(R.id.list_fragment)
  void onLongClick(LightModel light) {
    Bundle params = new Bundle();
    params.putSerializable(PARAM_VALUE, light);
    showEditDialog(params);
  }
  
  /* Dialogs */

  @Override
  public void editDialog(LightModel value) {
    EditLightActivity_.intent(this).intentType(EDIT).model(value).start();
  }
  
  @Override
  public void confirmDialog(LightModel value) {
    repository.delete(value.getId());
    adapter.notifyDataSetChanged();
  }

}
