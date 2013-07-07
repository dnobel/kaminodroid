package org.kaminodroid.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Application extends Entity {

	private List<Artifact> artifacts;

	private String description;

	private String name;

	public Application() {
		artifacts = Lists.newArrayList();
	}

	public List<Artifact> getArtifacts() {
		ArrayList<Artifact> artifacts = Lists.newArrayList(this.artifacts);
		Collections.sort(artifacts);
		Collections.reverse(artifacts);
		return artifacts;
	}

	public String getDescription() {
		return description;
	}

	public Artifact getLatestArtifact() {
		List<Artifact> artifacts = getArtifacts();
		if (!artifacts.isEmpty()) {
			return artifacts.get(0);
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setArtifacts(List<Artifact> artifacts) {
		this.artifacts = artifacts;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

}
