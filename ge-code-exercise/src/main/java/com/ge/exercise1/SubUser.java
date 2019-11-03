package com.ge.exercise1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubUser extends User {

	@JsonCreator
	public SubUser(@JsonProperty("id") String id, @JsonProperty("name") String name) {
		super(id.trim(), name.trim());
	}

}
