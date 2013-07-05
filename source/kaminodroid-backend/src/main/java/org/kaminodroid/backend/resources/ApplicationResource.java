package org.kaminodroid.backend.resources;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kaminodroid.api.Application;
import org.kaminodroid.backend.em.EntityManager;
import org.kaminodroid.backend.em.OrientDbEntityManager;

@Path("applications")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ApplicationResource extends AbstractResource {

	private final EntityManager<Application> entityManager;

	public ApplicationResource() {
		entityManager = createEntityManager();
	}

	@POST
	public String create(Application application) {
		application.setUuid(UUID.randomUUID().toString());
		getDatabase().save(application);
		return application.getUuid();
	}

	@GET
	public List<Application> getAll() {
		return this.entityManager.getAll();
	}

	@GET
	@Path("/{applicationUUid}")
	public Application getById(@PathParam("applicationUUid") String applicationUuid) {
		return this.entityManager.getById(applicationUuid);
	}

	private EntityManager<Application> createEntityManager() {
		return OrientDbEntityManager.forEntityType(Application.class, getDatabase());
	}
}
