package com.domotic.enhanced.fragment.ipcam;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.model.IpCamModel;

@EViewGroup(R.layout.item_ipcam)
public class IpCamItemView extends LinearLayout {
  
  @ViewById(R.id.textView_ipcamName)
  TextView textName;

  public IpCamItemView(Context context) {
    super(context);
  }
  
  public void bind(IpCamModel ipcam) {
    textName.setText(ipcam.getName());
  }

}
