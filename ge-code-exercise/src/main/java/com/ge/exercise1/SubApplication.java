package com.ge.exercise1;

import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubApplication extends Application {

	private Collection<SubUser> users = new HashSet<SubUser>();

	private Collection<SubGroup> groups = new HashSet<SubGroup>();

	@JsonCreator
	public SubApplication(@JsonProperty("id") String id, @JsonProperty("name") String name) {
		super(id.trim(), name.trim());
	}

	@Override
	public User getUser(String userId) {
		Collection<SubUser> totalUsers = new HashSet<SubUser>();
		totalUsers.addAll(this.users);
		if (this.groups != null) {
			for (SubGroup grp : this.groups) {
				totalUsers.addAll(grp.getUsers());
			}
		}
		return totalUsers.stream().filter(p -> p.getId().equals(userId)).findAny().orElse(null);
	}

	@Override
	public Collection<Group> getGroups() {
		return new HashSet<Group>(this.groups);
	}

	@Override
	public Group getGroup(String groupId) {
		return this.groups.stream().filter(p -> p.getId().equals(groupId)).findAny().orElse(null);
	}

	@Override
	public Collection<User> getUsers() {
		return new HashSet<User>(this.users);
	}

}
