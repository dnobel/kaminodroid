package org.kaminodroid.backend.entities;

import java.net.URL;

public class Artifact extends Entity {

    public String version;

    private URL downloadUrl;

    public URL getDownloadUrl() {
        return downloadUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setDownloadUrl(URL downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
