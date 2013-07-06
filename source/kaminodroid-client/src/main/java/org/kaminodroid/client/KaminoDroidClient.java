package org.kaminodroid.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.kaminodroid.api.Application;
import org.kaminodroid.api.Artifact;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class KaminoDroidClient {

    private static final String APPLICATIONS_RESOURCE = "applications";

    private final Client client;

    private final String restUrl;

    public KaminoDroidClient(String restUrl) {
        this.restUrl = restUrl;
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(clientConfig);
    }

    public void createApplication(Application application) {
        WebResource webResource = client.resource(getApplicationResourceUrl());
        webResource.entity(application, MediaType.APPLICATION_JSON).post();
    }

    public void createArtifact(String applicationId, Artifact artifact) {
        WebResource webResource = client.resource(getApplicationResourceUrl() + "/" + applicationId + "/artifacts");
        webResource.entity(artifact, MediaType.APPLICATION_JSON).post();
    }

    public Application getApplication(String uuid) {
        WebResource webResource = client.resource(getApplicationResourceUrl() + "/" + uuid);

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() == Status.NO_CONTENT.getStatusCode()) {
            return null;
        }

        checkResponse(response);

        return response.getEntity(Application.class);
    }

    public List<Application> getApplications() {
        WebResource webResource = client.resource(getApplicationResourceUrl());

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        checkResponse(response);

        return response.getEntity(new GenericType<List<Application>>() {
        });
    }


    private void checkResponse(ClientResponse response) {
        if (response.getStatus() != Status.OK.getStatusCode()) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
    }

    private String getApplicationResourceUrl() {
        return restUrl + APPLICATIONS_RESOURCE;
    }
}
