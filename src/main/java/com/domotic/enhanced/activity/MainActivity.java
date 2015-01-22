package com.domotic.enhanced.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
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
import com.domotic.enhanced.fragment.light.LightFragment;
import com.domotic.enhanced.fragment.light.LightFragment_;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

  private static final Logger log = LoggerFactory.getLogger(MainActivity.class);
  
  @ViewById(R.id.drawer_layout)
  DrawerLayout mDrawerLayout;
  
  @ViewById(R.id.left_drawer)
  ListView mDrawerList;
  
  // TODO repository MENU
  @StringArrayRes(R.array.drawer_menu_array)
  String[] mDrawerMenuTitles;
  
  private ActionBarDrawerToggle mDrawerToggle;
  private CharSequence mDrawerTitle;
  private CharSequence mTitle;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    mTitle = mDrawerTitle = getTitle();
    // enable ActionBar app icon to behave as action to toggle nav drawer
    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setHomeButtonEnabled(true);
  }

  @AfterViews
  void initViews() {
    // set a custom shadow that overlays the main content when the drawer opens
    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    // set up the drawer's list view with items and click listener
    mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_drawer, mDrawerMenuTitles));
    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    
    mDrawerToggle = mainActionBarDrawerToggle();
    mDrawerLayout.setDrawerListener(mDrawerToggle);
  }
  
  private ActionBarDrawerToggle mainActionBarDrawerToggle() {
    return new ActionBarDrawerToggle(
      this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

        @Override
        public void onDrawerOpened(View drawerView) {
          getActionBar().setTitle(mTitle);
          // creates call to onPrepareOptionsMenu()
          invalidateOptionsMenu();
        }

        @Override
        public void onDrawerClosed(View drawerView) {
          getActionBar().setTitle(mDrawerTitle);
          // creates call to onPrepareOptionsMenu()
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
    // TODO start fragment
    log.debug("selectItem: {}", position);
    
    // TODO
    LightFragment fragment = new LightFragment_();
    /*
    FragmentManager fragmentManager = getFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    //transaction.addToBackStack(null);
    */
    getFragmentManager()
      .beginTransaction()
      .replace(R.id.content_frame, fragment)
      //.addToBackStack(null)
      .commit();
    
    // update selected item and title, then close the drawer
    mDrawerList.setItemChecked(position, true);
    setTitle(mDrawerMenuTitles[position]);
    mDrawerLayout.closeDrawer(mDrawerList);
  }
  
  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    // pass any configuration change to the drawer toggle
    mDrawerToggle.onConfigurationChanged(newConfig);
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // the action bar home/up action should open or close the drawer
    // ActionBarDrawerToggle will take care of this
    if (mDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    // sync the toggle state after onRestoreInstanceState has occurred
    mDrawerToggle.syncState();
  }

  // called whenever we call invalidateOptionsMenu()
  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    // if the nav drawer is open, hide action items related to the content view
    boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
    //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
    return super.onPrepareOptionsMenu(menu);
  }

  @Override
  public void setTitle(CharSequence title) {
    mTitle = title;
    getActionBar().setTitle(mTitle);
  }

}
