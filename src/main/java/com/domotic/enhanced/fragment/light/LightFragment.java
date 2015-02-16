package com.domotic.enhanced.fragment.light;

import static com.domotic.enhanced.activity.ActivityIntentType.ADD;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.domotic.enhanced.R;
import com.domotic.enhanced.activity.EditLightActivity_;
import com.domotic.enhanced.fragment.dialog.EditDialogFragment;
import com.domotic.enhanced.fragment.dialog.EditDialogFragment.EditListenerDialogFragment;
import com.domotic.enhanced.model.LightModel;

@EFragment(R.layout.fragment_light)
@OptionsMenu(R.menu.menu_light)
public class LightFragment extends Fragment implements EditListenerDialogFragment {
  
  @ViewById(R.id.list_light)
  ListView list;
  
  @Bean(LightListAdapter.class)
  BaseAdapter adapter;
  
  @Bean(EditDialogFragment.class)
  EditDialogFragment<LightFragment> editDialog;

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
    editDialog.show(getFragmentManager(), getClass().getSimpleName());
  }
  
  /*
   * 
   */

  @Override
  public void edit() {
    Toast.makeText(getActivity(), "EDIT", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void delete() {
    Toast.makeText(getActivity(), "DELETE", Toast.LENGTH_SHORT).show();
  }
  
}
