package org.kaminodroid.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.kaminodroid.api.Application;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class KaminoDroidClient {

    public static void main(String[] args) {
        KaminoDroidClient kaminoDroidClient = new KaminoDroidClient("http://localhost:8080/rest/");
        System.out.println(kaminoDroidClient.getApplications());
    }

    private final Client client;

    private final String restUrl;

    public KaminoDroidClient(String restUrl) {
        this.restUrl = restUrl;
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(clientConfig);
    }

    public Application getApplication(String uuid) {
        WebResource webResource = client.resource(restUrl + "applications/" + uuid);

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        checkResponse(response);

        return response.getEntity(Application.class);
    }

    public List<Application> getApplications() {
        WebResource webResource = client.resource(restUrl + "applications");

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
}
