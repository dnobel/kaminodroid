package org.kaminodroid.backend.em;

import java.util.List;

import org.kaminodroid.api.Entity;

import com.google.common.collect.Lists;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class OrientDbEntityManager<T extends Entity> implements EntityManager<T> {

    public static <E extends Entity> OrientDbEntityManager<E> forEntityType(Class<E> entityType,
            OObjectDatabaseTx database) {
        return new OrientDbEntityManager<E>(entityType, database);
    }
    private final OObjectDatabaseTx database;

    private final Class<? extends Entity> entityType;

    private OrientDbEntityManager(Class<? extends Entity> entityType, OObjectDatabaseTx database) {
        this.entityType = entityType;
        this.database = database;
    }

    public List<T> getAll() {
        List<T> deteachedEntities = Lists.newArrayList();

        Iterable<? extends Entity> entities = database.browseClass(this.entityType);
        for (Entity entity : entities) {
            deteachedEntities.add(resolveEntitiy(entity));
        }

        return deteachedEntities;
    }

    public T getById(String id) {

        Iterable<? extends Entity> entities = database.browseClass(this.entityType);
        for (Entity entity : entities) {
            if (entity.getId().equals(id)) {
                return resolveEntitiy(entity);
            }
        }

        return null;
    }


    private T resolveEntitiy(Entity entity) {
        T detachedEntity = this.database.detachAll(entity, true);
        return detachedEntity;
    }
}
