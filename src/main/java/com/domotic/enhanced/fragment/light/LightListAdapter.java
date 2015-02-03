package com.domotic.enhanced.fragment.light;

import java.util.List;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.domotic.enhanced.model.Light;
import com.domotic.enhanced.repository.LightRepository;
import com.domotic.enhanced.repository.LightRepositoryMockImpl;

@EBean
public class LightListAdapter extends BaseAdapter {

  private List<Light> items;

  @Bean(LightRepositoryMockImpl.class)
  //@Bean(LightRepositoryImpl.class)
  LightRepository repository;

  @RootContext
  Context context;

  @AfterInject
  void initAdapter() {
    items = repository.findAll();
  }

  @Override
  public int getCount() {
    return items.size();
  }

  @Override
  public Light getItem(int position) {
    return items.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
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
