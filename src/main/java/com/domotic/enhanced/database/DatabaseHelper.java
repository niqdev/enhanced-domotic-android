package com.domotic.enhanced.database;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.domotic.enhanced.R;
import com.domotic.enhanced.model.Light;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
  
  private static final Logger log = LoggerFactory.getLogger(DatabaseHelper.class);
  
  private static final String DATABASE_NAME = "enhancedDomotic.db";
  private static final int DATABASE_VERSION = 1;
  
  public DatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
  }

  @Override
  public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    try {
      log.info("onCreate");
      TableUtils.createTable(connectionSource, Light.class);
    } catch (SQLException e) {
      log.error("Can't create database", e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase database,
      ConnectionSource connectionSource, int oldVersion, int newVersion) {
    try {
      log.info("onUpgrade");
      TableUtils.dropTable(connectionSource, Light.class, true);
      // after we drop the old databases, we create the new ones
      onCreate(database, connectionSource);
    } catch (SQLException e) {
      log.error("Can't drop databases", e);
      throw new RuntimeException(e);
    }
  }  

}
