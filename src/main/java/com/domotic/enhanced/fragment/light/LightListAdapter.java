package com.domotic.enhanced.fragment.light;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import android.view.View;
import android.view.ViewGroup;

import com.domotic.enhanced.fragment.AbstractListAdapter;
import com.domotic.enhanced.model.LightModel;
import com.domotic.enhanced.repository.CommonRepository;
import com.domotic.enhanced.repository.LightRepository;
import com.domotic.enhanced.repository.impl.LightRepositoryImpl;

@EBean
public class LightListAdapter extends AbstractListAdapter<LightModel> {

  //@Bean(LightRepositoryMockImpl.class)
  @Bean(LightRepositoryImpl.class)
  LightRepository repository;
  
  @Override
  protected CommonRepository<LightModel, Long> getRepository() {
    return repository;
  }
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LightItemView itemView;
    if (convertView == null) {
      itemView = LightItemView_.build(context);
    } else {
      itemView = (LightItemView) convertView;
    }
    itemView.bind(getItem(position));
    return itemView;
  }

}
