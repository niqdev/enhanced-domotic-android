package com.domotic.enhanced.fragment.light;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.domotic.enhanced.R;

@EFragment(R.layout.fragment_light)
public class LightListFragment extends ListFragment {// implements LoaderCallbacks<List<Light>> {
  
  private static final Logger log = LoggerFactory.getLogger(LightListFragment.class);
  
  @ViewById
  ListView list;
  
  @Bean(LightListAdapter.class)
  BaseAdapter adapter;
  
  @AfterViews
  void bindAdapter() {
    list.setAdapter(adapter);
  }
  
  

  /*
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //getActivity().setTitle("TITLE");
    
    return null;
  }
  
  @Bean(EnhancedLightImpl.class)
  EnhancedLight enhancedLight;

  @CheckedChange(R.id.switch_light)
  public void switchLight(boolean isChecked, CompoundButton button) {
    log.debug("switchLight: {}", isChecked);
    if (isChecked) {
      enhancedLight.turnOnById(21);
    } else {
      enhancedLight.turnOffById(21);
    }
  }
  */
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setListShown(true);
  }




}
