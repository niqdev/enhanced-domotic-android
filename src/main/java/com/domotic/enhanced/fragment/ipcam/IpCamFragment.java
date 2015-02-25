package com.domotic.enhanced.fragment.ipcam;

import static com.domotic.enhanced.activity.ActivityIntentType.ADD;
import static com.domotic.enhanced.activity.ActivityIntentType.EDIT;
import static com.domotic.enhanced.fragment.dialog.AbstractDialogFragment.PARAM_VALUE;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  
  private static final Logger log = LoggerFactory.getLogger(IpCamFragment.class);
  
  private static final boolean MOCK = true;
  
  @Bean(IpCamListAdapter.class)
  BaseAdapter adapter;
  
  @Bean(IpCamRepositoryImpl.class)
  IpCamRepository repository;
  
  @Override
  public BaseAdapter getBaseAdapter() {
    return adapter;
  }
  
  @AfterInject
  void initMock() {
    if (MOCK && repository.count() == 0) {
      
      IpCamModel ipcam1 = new IpCamModel();
      ipcam1.setUrl("http://plazacam.studentaffairs.duke.edu/mjpg/video.mjpg");
      ipcam1.setName("test1");
      repository.add(ipcam1);
      
      IpCamModel ipcam2 = new IpCamModel();
      ipcam2.setUrl("http://wmccpinetop.axiscam.net/mjpg/video.mjpg");
      ipcam2.setName("test2");
      repository.add(ipcam2);
    }
  }
  
  @OptionsItem(R.id.action_ipcam_add)
  void addIpcam() {
    EditIpCamActivity_.intent(this).intentType(ADD).start();
  }
  
  @ItemClick(R.id.list_fragment)
  void onClick(IpCamModel ipcam) {
    IpCamFullActivity_.intent(this).ipcam(ipcam).start();
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
    repository.delete(value.getId());
    adapter.notifyDataSetChanged();
  }

}
