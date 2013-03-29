package org.kaminodroid.backend.entities;

import java.util.List;

public class Application extends Entity {

    private List<Artifact> artifacts;
    private String name;

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public String getName() {
        return name;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public void setName(String name) {
        this.name = name;
    }
}
