package com.domotic.enhanced.fragment.light;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.activity.EditLightActivity_;

@EFragment(R.layout.fragment_light)
public class LightFragment extends Fragment {
  
  @ViewById(R.id.list_light)
  ListView list;
  
  @Bean(LightListAdapter.class)
  BaseAdapter adapter;

  @AfterViews
  void bindAdapter() {
    list.setAdapter(adapter);
  }
  
  @Click(R.id.button_addLight)
  void addLight() {
    EditLightActivity_.intent(this).start();
  }

  @Override
  public void onResume() {
    super.onResume();
    adapter.notifyDataSetChanged();
  }
  
}
