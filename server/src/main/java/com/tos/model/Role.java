package com.tos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Role")
@Table(name = "Role")
public class Role {

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name.toString();
	}

	public void setName(String name) {
		this.name = name;
	}

}
