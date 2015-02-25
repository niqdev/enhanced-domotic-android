package com.domotic.enhanced.activity;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.IOException;
import java.net.URI;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.domotic.enhanced.R;
import com.domotic.enhanced.mjpeg.MjpegInputStream;
import com.domotic.enhanced.mjpeg.MjpegView;
import com.domotic.enhanced.model.IpCamModel;

@EActivity(R.layout.activity_ipcam)
public class IpCamFullActivity extends Activity {
  
  private static final Logger log = LoggerFactory.getLogger(IpCamFullActivity.class);
  
  @Extra
  IpCamModel ipcam;
  
  @ViewById(R.id.linearLayout_ipcam)
  LinearLayout layout;
  
  @StringRes(R.string.error_connection)
  String errorConnection;
  
  @StringRes(R.string.error_url)
  String errorUrl;
  
  private MjpegView mv;
  
  @AfterViews
  void bindView() {
    mv = new MjpegView(this);
    layout.addView(mv);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    loadIpcam();
  }

  @Override
  public void onPause() {
    super.onPause();
    mv.stopPlayback();
  }
  
  @OptionsItem(android.R.id.home)
  void onBackAction() {
    // action back button same behaviour
    this.onBackPressed();
  }
  
  @Background
  void loadIpcam() {
    try {
      String url = ipcam.getUrl();
      log.debug("load ipcam URL: {}", url);
      
      HttpClient httpclient = initHttpClient();
      HttpResponse response = httpclient.execute(new HttpGet(URI.create(url)));
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        showIpcam(new MjpegInputStream(response.getEntity().getContent()));
      }
    } catch (IOException e) {
      log.error("loadIpcam-IOException", e);
      showError(errorConnection);
    } catch (Throwable e) {
      log.error("loadIpcam-Throwable", e);
      showError(errorUrl);
    }
  }
  
  private HttpClient initHttpClient() {
    if (isBlank(ipcam.getName()) || isBlank(ipcam.getPassword())) {
      return new DefaultHttpClient();
    }
    
    HttpParams httpParams = new BasicHttpParams();
    httpParams.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
    // increased timeout
    HttpConnectionParams.setConnectionTimeout(httpParams, 100000);
    HttpConnectionParams.setSoTimeout(httpParams, 100000);
    
    CredentialsProvider provider = new BasicCredentialsProvider();
    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(ipcam.getName(), ipcam.getPassword());
    provider.setCredentials(AuthScope.ANY, credentials);
        
    DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
    httpClient.setCredentialsProvider(provider);
    return httpClient;
  }
  
  @UiThread
  void showError(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }
  
  @UiThread
  void showIpcam(MjpegInputStream source) {
    mv.setSource(source);
    mv.setDisplayMode(MjpegView.SIZE_BEST_FIT);
    mv.showFps(true);
  }

}
