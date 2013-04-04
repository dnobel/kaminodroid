package org.kaminodroid.api;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

public class Artifact extends Entity implements Comparable<Artifact> {

    public String version;

    private String downloadUrl;

    public int compareTo(Artifact anotherArtifact) {
        DefaultArtifactVersion artifactVersion1 = new DefaultArtifactVersion(this.getVersion());
        DefaultArtifactVersion artifactVersion2 = new DefaultArtifactVersion(anotherArtifact.getVersion());
        return artifactVersion1.compareTo(artifactVersion2);
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
