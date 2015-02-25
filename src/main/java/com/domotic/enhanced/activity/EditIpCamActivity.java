package com.domotic.enhanced.activity;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.widget.EditText;
import android.widget.TextView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.model.IpCamModel;
import com.domotic.enhanced.repository.IpCamRepository;
import com.domotic.enhanced.repository.impl.IpCamRepositoryImpl;
import com.mobsandgeeks.saripaar.annotation.Required;

@EActivity(R.layout.edit_ipcam)
public class EditIpCamActivity extends AbstractValidationActivity<IpCamModel, Long> {

  @Extra
  IpCamModel model;

  @Bean(IpCamRepositoryImpl.class)
  IpCamRepository repository;

  @ViewById(R.id.editText_editIpcamUrl)
  @Required(order = 1, messageResId = R.string.validation_required)
  // TODO @Regex
  EditText editTextUrl;

  @ViewById(R.id.editText_editIpcamName)
  @Required(order = 2, messageResId = R.string.validation_required)
  EditText editTextName;

  @ViewById(R.id.editText_editIpcamUsername)
  EditText editTextUsername;

  @ViewById(R.id.editText_editIpcamPassword)
  EditText editTextPassword;

  @AfterTextChange({
    R.id.editText_editIpcamUrl,
    R.id.editText_editIpcamName
  })
  void clearValidation(TextView textView) {
    super.clearAllValidation(textView);
  }

  @Override
  void initAddViews() {
    // ready
  }

  @Override
  void initEditViews() {
    setButtonTag(model.getId());

    editTextUrl.setText(model.getUrl());
    editTextName.setText(model.getName());
    editTextUsername.setText(model.getUsername());
    editTextPassword.setText(model.getPassword());
  }

  @Override
  void onAddValidationSucceeded() {
    repository.add(parseIpCam());
  }

  @Override
  void onEditValidationSucceeded() {
    repository.update(parseIpCam());
  }

  private IpCamModel parseIpCam() {
    IpCamModel ipcam = new IpCamModel();
    ipcam.setId(getButtonTag());
    ipcam.setUrl(editTextUrl.getText().toString());
    ipcam.setName(editTextName.getText().toString());
    ipcam.setUsername(editTextUsername.getText().toString());
    // TODO no clear text
    ipcam.setPassword(editTextPassword.getText().toString());
    return ipcam;
  }

}
