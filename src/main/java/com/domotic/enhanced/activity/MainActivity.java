package com.domotic.enhanced.activity;

import java.util.List;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.domotic.enhanced.R;
import com.domotic.enhanced.model.DrawerMenuModel;
import com.domotic.enhanced.repository.DrawerMenuRepository;
import com.domotic.enhanced.repository.impl.DrawerMenuRepositoryImpl;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

  private static final Logger log = LoggerFactory.getLogger(MainActivity.class);
  
  @ViewById(R.id.drawer_layout)
  DrawerLayout mDrawerLayout;
  
  @ViewById(R.id.left_drawer)
  ListView mDrawerList;
  
  @Bean(DrawerMenuRepositoryImpl.class)
  DrawerMenuRepository drawerMenuRepository;
  
  private ActionBarDrawerToggle mDrawerToggle;
  private List<DrawerMenuModel> mDrawerMenu;
  
  @AfterInject
  void initDrawerMenuTitles() {
    mDrawerMenu = drawerMenuRepository.findAll();
  }
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setHomeButtonEnabled(true);
  }

  @AfterViews
  void initViews() {
    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    
    String[] findAllTitles = drawerMenuRepository.findLabels();
    mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_drawer, findAllTitles));
    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    mDrawerToggle = mainActionBarDrawerToggle();
    mDrawerLayout.setDrawerListener(mDrawerToggle);
    
    // TODO
    selectItem(1);
  }
  
  private ActionBarDrawerToggle mainActionBarDrawerToggle() {
    return new ActionBarDrawerToggle(
      this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

        @Override
        public void onDrawerOpened(View drawerView) {
          invalidateOptionsMenu();
        }

        @Override
        public void onDrawerClosed(View drawerView) {
          invalidateOptionsMenu();
        }
    }; 
  }
  
  private class DrawerItemClickListener implements ListView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      selectItem(position);
    }
  }
  
  private void selectItem(int position) {
    log.debug("selectItem: {}", position);
    startFragment(mDrawerMenu.get(position).<Fragment>newInstance());
    mDrawerList.setItemChecked(position, true);
    String[] findAllActionTitles = drawerMenuRepository.findActionBarTitles();
    setTitle(findAllActionTitles[position]);
    mDrawerLayout.closeDrawer(mDrawerList);
  }
  
  void startFragment(Fragment fragment) {
    getFragmentManager()
      .beginTransaction()
      .replace(R.id.content_frame, fragment)
      //.addToBackStack(null)
      .commit();
  }
  
  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    mDrawerToggle.onConfigurationChanged(newConfig);
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (mDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mDrawerToggle.syncState();
  }

  // TODO called whenever we call invalidateOptionsMenu()
  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    // if the nav drawer is open, hide action items related to the content view
    boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
    //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
    return super.onPrepareOptionsMenu(menu);
  }

}
