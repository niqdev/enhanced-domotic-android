package com.domotic.enhanced.fragment.ipcam;

import static com.domotic.enhanced.activity.ActivityIntentType.ADD;
import static com.domotic.enhanced.activity.ActivityIntentType.EDIT;
import static com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_VALUE;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import android.os.Bundle;
import android.widget.BaseAdapter;

import com.domotic.enhanced.R;
import com.domotic.enhanced.activity.EditIpCamActivity_;
import com.domotic.enhanced.activity.IpCamFullActivity_;
import com.domotic.enhanced.fragment.AbstractFragmentList;
import com.domotic.enhanced.model.IpCamModel;
import com.domotic.enhanced.repository.IpCamRepository;
import com.domotic.enhanced.repository.impl.IpCamRepositoryImpl;

@EFragment(R.layout.fragment_list)
@OptionsMenu(R.menu.menu_ipcam)
public class IpCamFragment extends AbstractFragmentList<IpCamModel> {
  
  @Bean(IpCamListAdapter.class)
  BaseAdapter adapter;
  
  @Bean(IpCamRepositoryImpl.class)
  IpCamRepository repository;
  
  @Override
  public BaseAdapter getBaseAdapter() {
    return adapter;
  }
  
  @OptionsItem(R.id.action_ipcam_add)
  void addIpcam() {
    EditIpCamActivity_.intent(this).intentType(ADD).start();
  }
  
  @ItemClick(R.id.list_fragment)
  void onClick(IpCamModel ipcam) {
    // TODO
    IpCamFullActivity_.intent(this).start();
  }
  
  @ItemLongClick(R.id.list_fragment)
  void onLongClick(IpCamModel ipcam) {
    Bundle params = new Bundle();
    params.putSerializable(PARAM_VALUE, ipcam);
    showEditDialog(params);
  }
  
  /* Dialogs */

  @Override
  public void editDialog(IpCamModel value) {
    EditIpCamActivity_.intent(this).intentType(EDIT).model(value).start();
  }

  @Override
  public void confirmDialog(IpCamModel value) {
    // TODO
    repository.delete(value.getId());
    adapter.notifyDataSetChanged();
  }

}
