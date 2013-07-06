package org.kaminodroid.mojo;

import java.io.File;
import java.io.IOException;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Build;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.kaminodroid.api.Application;
import org.kaminodroid.client.KaminoDroidArtifactUploader;
import org.kaminodroid.client.KaminoDroidClient;

@Mojo(name = "kamino")
public class KaminoDroidMojo extends AbstractMojo {

    @Component
    private MavenProject mavenProject;

    @Parameter(property = "kamino.url")
    private String url;

    public void execute() throws MojoExecutionException, MojoFailureException {

        KaminoDroidArtifactUploader artifactUploader = new KaminoDroidArtifactUploader(this.url + "/artifacts");

        String downloadUrl = null;

        try {
            downloadUrl = artifactUploader.uploadFile(getArtifactFile());
        }
        catch (IOException ex) {
            throw new MojoExecutionException("Could not upload artifact to KaminoDroid: " + ex.getMessage(), ex);
        }

        getLog().info("Uploaded Maven artifact file. Download URL: " + downloadUrl);

        KaminoDroidClient client = new KaminoDroidClient(this.url + "/rest/");

        Artifact artifact = mavenProject.getArtifact();
        String applicationId = artifact.getGroupId() + ":" + artifact.getArtifactId();
        Application application = client.getApplication(applicationId);

        if (application == null) {
            getLog().info("Creating KaminoDroid application: " + applicationId);

            application = new Application();
            application.setId(applicationId);
            application.setName(mavenProject.getName());
            client.createApplication(application);
        }

        getLog().info("Creating KaminoDroid artifact: " + artifact.getVersion());

        org.kaminodroid.api.Artifact kaminoArtifact = new org.kaminodroid.api.Artifact();
        kaminoArtifact.setVersion(artifact.getVersion());
        kaminoArtifact.setDownloadUrl(downloadUrl);

        client.createArtifact(applicationId, kaminoArtifact);
    }

    private File getArtifactFile() {
        Build build = mavenProject.getBuild();
        String artifactFileName = build.getDirectory() + "/" + build.getFinalName() + "." + mavenProject.getPackaging();
        File artifactFile = new File(artifactFileName);
        return artifactFile;
    }

}
