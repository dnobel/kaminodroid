package org.kaminodroid.api;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Artifact extends Entity implements Comparable<Artifact> {

	public String version;

	private String downloadUrl;

	@JsonIgnore
	private Application application;

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
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

	public int compareTo(Artifact anotherArtifact) {
		DefaultArtifactVersion artifactVersion1 = new DefaultArtifactVersion(this.getVersion());
		DefaultArtifactVersion artifactVersion2 = new DefaultArtifactVersion(anotherArtifact.getVersion());
		return artifactVersion1.compareTo(artifactVersion2);
	}

}
