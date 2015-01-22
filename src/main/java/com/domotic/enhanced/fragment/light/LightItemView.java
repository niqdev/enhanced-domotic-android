package com.domotic.enhanced.fragment.light;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.model.Light;

@EViewGroup(R.layout.item_light)
public class LightItemView extends LinearLayout {
  
  @ViewById(R.id.light_id)
  TextView idView;
  
  @ViewById(R.id.light_description)
  TextView descriptionView;

  public LightItemView(Context context) {
    super(context);
  }
  
  public void bind(Light light) {
    //idView.setText(light.getId());
    descriptionView.setText(light.getDescription());
  }

}
