package com.domotic.enhanced.fragment;

import java.io.Serializable;
import java.util.List;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.widget.BaseAdapter;

import com.domotic.enhanced.repository.CommonRepository;

@EBean
public abstract class AbstractListAdapter<T extends Serializable> extends BaseAdapter {
  
  private List<T> items;
  
  @RootContext
  protected Context context;
  
  protected abstract CommonRepository<T, Long> getRepository();

  @AfterInject
  protected void initAdapter() {
    items = getRepository().findAll();
  }

  @Override
  public int getCount() {
    return items.size();
  }

  @Override
  public T getItem(int position) {
    return items.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public void notifyDataSetChanged() {
    // refresh list
    initAdapter();
    super.notifyDataSetChanged();
  }

}
