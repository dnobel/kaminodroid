package org.kaminodroid.backend.resources;

import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public abstract class AbstractResource {

    protected OObjectDatabaseTx getDatabase() {
        return OObjectDatabasePool.global().acquire("local:db", "admin", "admin");
    }
}
