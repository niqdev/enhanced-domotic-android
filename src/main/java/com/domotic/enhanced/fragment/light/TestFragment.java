package com.domotic.enhanced.fragment.light;

import org.androidannotations.annotations.EFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.domotic.enhanced.R;

@EFragment(R.layout.fragment_light)
public class TestFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    getActivity().setTitle("TITLE");
    return null;
  }
  
  

}
