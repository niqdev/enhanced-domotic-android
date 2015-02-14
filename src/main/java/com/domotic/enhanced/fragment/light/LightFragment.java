package com.domotic.enhanced.fragment.light;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.activity.EditLightActivity_;

@EFragment(R.layout.fragment_light)
@OptionsMenu(R.menu.menu_light)
public class LightFragment extends Fragment {
  
  @ViewById(R.id.list_light)
  ListView list;
  
  @Bean(LightListAdapter.class)
  BaseAdapter adapter;

  @AfterViews
  void bindAdapter() {
    list.setAdapter(adapter);
  }
  
  @OptionsItem(R.id.action_light_add)
  void addLight() {
    EditLightActivity_.intent(this).start();
  }

  @Override
  public void onResume() {
    super.onResume();
    adapter.notifyDataSetChanged();
  }
  
}
