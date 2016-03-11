package com.businessLogic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScannedShow {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("properties")
	private String properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}
}
