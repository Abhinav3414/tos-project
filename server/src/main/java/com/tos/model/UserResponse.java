package com.tos.model;

import java.util.Set;

public class UserResponse {

	public Long id;
	public String username;
	public String email;
	public Set<Role> roles;

	public UserResponse() {
	}

	public UserResponse(Long id, String username, String email, Set<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
	
}
