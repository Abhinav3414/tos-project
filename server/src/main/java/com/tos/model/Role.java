package com.tos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Role")
@Table(name = "Role")
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	String name;

	Role() {
	}

	public Role(Roles admin) {
		this.name = admin.toString();
	}

	public String getName() {
		return name.toString();
	}

	public void setName(Roles name) {
		this.name = name.toString();
	}

}
