package com.businessLogic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScannedShow {
	
	public ScannedShow() {
	}
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("properties")
	private ShowProperties properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ShowProperties getProperties() {
		return properties;
	}

	public void setProperties(ShowProperties properties) {
		this.properties = properties;
	}
}
