package com.ge.exercise1;

import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubGroup extends Group {

	private Collection<SubUser> users = new HashSet<SubUser>();

	public Collection<SubUser> getUsers() {
		return users;
	}

	public void setUsers(Collection<SubUser> users) {
		this.users = users;
		super.size = users.size();
	}

	@JsonCreator
	public SubGroup(@JsonProperty("id") String id, @JsonProperty("name") String name) {
		super(id.trim(), name.trim());
	}

}
