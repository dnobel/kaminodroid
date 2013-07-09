package org.kaminodroid.backend.resources;

import org.kaminodroid.backend.DatabaseProvider;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public abstract class AbstractResource {

    protected OObjectDatabaseTx getDatabase() {
        return DatabaseProvider.getDatabase();
    }
}
