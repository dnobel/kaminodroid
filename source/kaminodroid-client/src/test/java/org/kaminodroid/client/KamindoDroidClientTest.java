package org.kaminodroid.client;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kaminodroid.api.Application;
import org.kaminodroid.api.Artifact;

public class KamindoDroidClientTest {

    private KaminoDroidClient kaminoDroidClient;

    @Test
    public void creatApplication() {

        Application application = new Application();
        application.setId("myId1");
        application.setName("MyApplication");

        kaminoDroidClient.createApplication(application);

        List<Application> applications = kaminoDroidClient.getApplications();
        Assert.assertTrue(!applications.isEmpty());
    }

    @Test
    public void createArtifact() {

        // Application application = new Application();
        // application.setId("myId2");
        // application.setName("MyApplication");
        //
        // kaminoDroidClient.createApplication(application);

        Artifact artifact = new Artifact();
        artifact.setVersion("1.0.0-201307051756");
        artifact.setDownloadUrl("http://itemis.de");

        kaminoDroidClient.createArtifact("myId2", artifact);
    }

    @Before
    public void setUp() {
        kaminoDroidClient = new KaminoDroidClient("http://localhost:8080/rest/");
    }
}
