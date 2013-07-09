package org.kaminodroid.backend;

import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class DatabaseProvider {

    private static OObjectDatabaseTx database;

    public static synchronized OObjectDatabaseTx getDatabase() {
        if (database == null) {
            database = OObjectDatabasePool.global().acquire("local:db", "admin", "admin");
        }
        return database;
    }

}
