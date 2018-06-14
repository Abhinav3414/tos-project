package com.tos.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

import com.tos.config.CustomUserDetails;
import com.tos.model.Role;
import com.tos.model.Roles;
import com.tos.model.User;
import com.tos.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired AuthenticationManagerBuilder builder;
	
	/* search all users*/
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	/*to save a user */
	public User addUser(User user) {
		//User tempUser = new User(user.getUsername(), user.getEmail(), user.getPassword(),Arrays.asList(new Role("USER"), new Role("ACTUATOR")));
	
		user.setRoles(Arrays.asList(new Role(Roles.USER)));
		userRepository.save(user);
		try {
			builder.userDetailsService(s -> new CustomUserDetails(userRepository.findByUsername(s)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
		
		
	}
	
	/*get a user by id*/
	public User getUser(Long userId) {
		return userRepository.findOne(userId);
	}

	/*to update a user*/
	public User updateUser(User userData, long id) {
		if(userRepository.findOne(id)==null) {
			return null;
		}
		return userRepository.save(userData);
	}
	
	/*delete a user*/
	public boolean deleteUser(Long userId) {
		if(userRepository.findOne(userId)==null) {
			return false;
		}
		userRepository.delete(userId);
		return true;
	}
	
	
}
