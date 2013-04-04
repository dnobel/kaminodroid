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
import org.kaminodroid.api.Artifact;

import com.google.common.collect.Lists;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

@Path("applications/{applicationUUid}/artifacts")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ArtifactResource extends AbstractResource {

    @POST
    public String create(@PathParam("applicationUUid") String applicationUuid, Artifact artifact) {
        Application application = getApplication(applicationUuid);
        if (application != null) {

            artifact.setUuid(UUID.randomUUID().toString());

            application.getArtifacts().add(artifact);

            getDatabase().save(application);

            return artifact.getUuid();
        }

        return null;
    }

    @GET
    public List<Artifact> getAll(@PathParam("applicationUUid") String applicationUuid) {

        List<Artifact> artifacts = Lists.newArrayList();

        Application application = getApplication(applicationUuid);

        for (Artifact artifact : application.getArtifacts()) {
            artifacts.add((Artifact) getDatabase().detachAll(artifact, true));
        }

        return artifacts;
    }

    private Application getApplication(String applicationUuid) {
        List<Application> applications = getDatabase().query(
                new OSQLSynchQuery<Artifact>("select * from " + Application.class.getSimpleName() + " where uuid = ?"),
                applicationUuid);
        if (!applications.isEmpty()) {
            return applications.get(0);
        }
        else {
            return null;
        }

    }
}
