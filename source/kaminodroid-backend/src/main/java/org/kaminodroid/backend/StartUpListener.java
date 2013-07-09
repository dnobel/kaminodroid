package org.kaminodroid.backend;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class StartUpListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce) {
        DatabaseProvider.getDatabase().close();
    }

    public void contextInitialized(ServletContextEvent sce) {
        OObjectDatabaseTx db = new OObjectDatabaseTx("local:db");
        if (!db.exists()) {
            db.create();
        }

        db = DatabaseProvider.getDatabase();
        db.getEntityManager().registerEntityClasses("org.kaminodroid.api");
    }

}
