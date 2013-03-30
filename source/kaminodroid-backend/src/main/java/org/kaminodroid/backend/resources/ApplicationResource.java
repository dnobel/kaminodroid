package org.kaminodroid.backend.resources;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kaminodroid.api.Application;

import com.google.common.collect.Lists;
import com.orientechnologies.orient.object.iterator.OObjectIteratorClass;

@Path("applications")
public class ApplicationResource extends AbstractResource {

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public String create(Application application) {
		application.setUuid(UUID.randomUUID().toString());
		getDatabase().save(application);
		return application.getUuid();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Application> getAll() {
		List<Application> applications = Lists.newArrayList();
		OObjectIteratorClass<Application> applicationIterator = getDatabase().browseClass(Application.class);
		for (Application application : applicationIterator) {
			applications.add((Application) getDatabase().detach(application, true));
		}

		return applications;
	}
}
