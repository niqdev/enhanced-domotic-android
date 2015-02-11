package com.domotic.enhanced.fragment.ipcam;

import java.io.IOException;
import java.net.URI;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
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

import android.app.Fragment;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.domotic.enhanced.R;
import com.domotic.enhanced.mjpeg.MjpegInputStream;
import com.domotic.enhanced.mjpeg.MjpegView;

@EFragment(R.layout.fragment_ipcam)
public class IpCamFragment extends Fragment {
  
  private static final Logger log = LoggerFactory.getLogger(IpCamFragment.class);
  
  @ViewById(R.id.linearLayout_ipcam)
  LinearLayout layout;
  
  private MjpegView mv;
  
  @AfterViews
  void bindView() {
    mv = new MjpegView(getActivity());
    layout.addView(mv);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO test
    loadIpcam("http://plazacam.studentaffairs.duke.edu/mjpg/video.mjpg");
  }

  @Override
  public void onPause() {
    super.onPause();
    mv.stopPlayback();
  }
  
  @Background
  void loadIpcam(String url) {
    HttpClient httpclient = new DefaultHttpClient();
    try {
      HttpResponse response = httpclient.execute(new HttpGet(URI.create(url)));
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        showIpcam(new MjpegInputStream(response.getEntity().getContent()));
      }
    } catch (ClientProtocolException e) {
      log.error("loadIpcam-ClientProtocolException", e);
    } catch (IOException e) {
      log.error("loadIpcam-IOException", e);
    }
  }
  
  private HttpClient getHttpClient() {
    HttpParams httpParams = new BasicHttpParams();
    httpParams.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
    // increased timeout
    HttpConnectionParams.setConnectionTimeout(httpParams, 100000);
    HttpConnectionParams.setSoTimeout(httpParams, 100000);
    
    CredentialsProvider provider = new BasicCredentialsProvider();
    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("", "");
    provider.setCredentials(AuthScope.ANY, credentials);
        
    DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
    // TODO httpClient.setCredentialsProvider(provider);
    return httpClient;
  }
  
  @UiThread
  void showIpcam(MjpegInputStream source) {
    mv.setSource(source);
    mv.setDisplayMode(MjpegView.SIZE_BEST_FIT);
    mv.showFps(true);
  }

}
