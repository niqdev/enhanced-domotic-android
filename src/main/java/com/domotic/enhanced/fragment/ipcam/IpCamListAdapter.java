package com.domotic.enhanced.fragment.ipcam;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import android.view.View;
import android.view.ViewGroup;

import com.domotic.enhanced.fragment.AbstractListAdapter;
import com.domotic.enhanced.model.IpCamModel;
import com.domotic.enhanced.repository.CommonRepository;
import com.domotic.enhanced.repository.IpCamRepository;
import com.domotic.enhanced.repository.impl.IpCamRepositoryImpl;

@EBean
public class IpCamListAdapter extends AbstractListAdapter<IpCamModel> {
  
  @Bean(IpCamRepositoryImpl.class)
  IpCamRepository repository;
  
  @Override
  protected CommonRepository<IpCamModel, Long> getRepository() {
    return repository;
  }
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    IpCamItemView itemView;
    if (convertView == null) {
      itemView = IpCamItemView_.build(context);
    } else {
      itemView = (IpCamItemView) convertView;
    }
    itemView.bind(getItem(position));
    return itemView;
  }

}
