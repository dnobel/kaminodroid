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
import org.kaminodroid.backend.entitymanagers.EntityManager;

import com.google.common.collect.Lists;

@Path("applications")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ApplicationResource extends AbstractResource {

    @POST
    public String create(Application application) {
        application.setUuid(UUID.randomUUID().toString());
        getDatabase().save(application);
        return application.getUuid();
    }

    @GET
    public List<Application> getAll() {
        List<Application> detachedApplications = Lists.newArrayList();
        List<Application> applications = getEntityManager().getAll();

        for (Application application : applications) {
            detachedApplications.add((Application) getDatabase().detachAll(application, true));
        }

        return detachedApplications;
    }

    @GET
    @Path("/{applicationUUid}")
    public Application getById(@PathParam("applicationUUid") String applicationUuid) {
        Application application = getEntityManager().getById(applicationUuid);
        if (application != null) {
            return getDatabase().detachAll(application, true);
        }
        else {
            return null;
        }
    }

    private EntityManager<Application> getEntityManager() {
        return EntityManager.forEntityType(Application.class, getDatabase());
    }
}
