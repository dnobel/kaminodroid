package org.kaminodroid.backend.em;

import java.util.List;

import org.kaminodroid.api.Entity;

public interface EntityManager<T extends Entity> {

	public T getById(String applicationUuid);

	public List<T> getAll();

}
