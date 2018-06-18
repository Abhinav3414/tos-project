package com.tos.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This class is UserDetailsService of OAuth2 security it maps users with OAuth
 * userDetails object and enriches them with authentication and authorization
 * features
 * 
 * @author Abhinav Gupta
 * @version 1.0
 * @since 15-06-2018
 */

public class CustomUserDetails extends User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	String ROLE_PREFIX = "ROLE_";

	Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(final User users) {
		super(users);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
