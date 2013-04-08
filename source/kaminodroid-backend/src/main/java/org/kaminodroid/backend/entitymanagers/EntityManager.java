package org.kaminodroid.backend.entitymanagers;

import java.util.List;

import org.kaminodroid.api.Entity;

import com.google.common.collect.Lists;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.object.iterator.OObjectIteratorClass;

public class EntityManager<T extends Entity> {

    public static <C extends Entity> EntityManager<C> forEntityType(Class<C> entityType, OObjectDatabaseTx database) {
        return new EntityManager<C>(entityType, database);
    }

    private final OObjectDatabaseTx database;

    final Class<T> entityType;

    public EntityManager(Class<T> entityType, OObjectDatabaseTx database) {
        this.entityType = entityType;
        this.database = database;
    }

    public void create(T entity) {
        database.save(entity);
    }

    public List<T> getAll() {
        List<T> entities = Lists.newArrayList();
        OObjectIteratorClass<T> entitiyIterator = database.browseClass(entityType);
        for (T entity : entitiyIterator) {
            entities.add(entity);
        }
        return entities;
    }

    public T getById(String uuid) {
        List<T> entities = database.query(new OSQLSynchQuery<T>("select * from " + entityType.getSimpleName()
                + " where uuid = ?"), uuid);
        if (!entities.isEmpty()) {
            return entities.get(0);
        }
        else {
            return null;
        }
    }

    public void remove(String uuid) {
        T entity = getById(uuid);

        if (entity == null) {
            throw new IllegalArgumentException(String.format("Entity with uuid '%s' does not exist", uuid));
        }

        remove(entity);
    }

    public void remove(T entity) {
        database.delete(entity);
    }

    public void update(T entity) {
        database.save(entity);
    }

}
