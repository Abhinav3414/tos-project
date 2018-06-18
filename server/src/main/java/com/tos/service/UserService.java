package com.tos.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tos.model.CustomUserDetails;
import com.tos.model.Role;
import com.tos.model.Roles;
import com.tos.model.User;
import com.tos.repository.RoleRepository;
import com.tos.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired AuthenticationManagerBuilder builder;
	
	/* search all users*/
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	
	/*to save a user */
	public User addUsers(User user) {
		//User tempUser = new User(user.getUsername(), user.getEmail(), user.getPassword(),Arrays.asList(new Role("USER"), new Role("ACTUATOR")));
		
/*		Role role_USER = roleRepository.findByName(Roles.GUEST.toString());
		if(role_USER != null) {
			System.out.println(role_USER.getName());
			System.out.println("*************************abhi **********************");
			user.setRoles(Arrays.asList(role_USER));
		} else
		{
			user.setRoles(Arrays.asList(new Role(Roles.USER.toString())));
			System.out.println("((((((((((((((((((((((((((");
		}
		
		userRepository.save(new User(user.getUsername(),user.getEmail(),user.getPassword(), Arrays.asList(user.getRoles().get(0))));*/
		user.setActive(true);
		userRepository.save(user);
		try {
			//builder.userDetailsService(s -> new CustomUserDetails(userRepository.findByUsername(s)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
		
		
	}
	
	@Autowired
	private AuthenticationManagerBuilder builders;
	public void addUser(User user) throws Exception {
		

		Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
		
		builders.userDetailsService(s -> new CustomUserDetails(userOptional));
		



		return userOptional.map(user -> new CustomUserDetails(user)).get();

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
