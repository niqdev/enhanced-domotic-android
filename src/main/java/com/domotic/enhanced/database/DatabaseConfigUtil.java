package com.domotic.enhanced.database;

import java.io.IOException;
import java.sql.SQLException;

import com.domotic.enhanced.model.DrawerMenuModel;
import com.domotic.enhanced.model.IpCamModel;
import com.domotic.enhanced.model.LightModel;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * RUN
 * mvn exec:java
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {

  private static final Class<?>[] classes = new Class[] {
    DrawerMenuModel.class,
    LightModel.class,
    IpCamModel.class
  };

  public static void main(String[] args) throws SQLException, IOException {
    writeConfigFile("ormlite_config.txt", classes);
  }

}
