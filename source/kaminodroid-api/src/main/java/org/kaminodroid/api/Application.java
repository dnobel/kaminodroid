package org.kaminodroid.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class Application extends Entity {

	private List<Artifact> artifacts;
	private String name;

	public Application() {
		artifacts = Lists.newArrayList();
	}

	public List<Artifact> getArtifacts() {
		return artifacts;
	}

	public String getName() {
		return name;
	}

	public Artifact getLatestArtifact() {
		if (getArtifacts() != null && getArtifacts().isEmpty()) {
			ArrayList<Artifact> artifacts = Lists.newArrayList(getArtifacts());
			Collections.sort(artifacts);
			return artifacts.get(0);
		}
		return null;
	}

	public void setArtifacts(List<Artifact> artifacts) {
		this.artifacts = artifacts;
	}

	public void setName(String name) {
		this.name = name;
	}

}
