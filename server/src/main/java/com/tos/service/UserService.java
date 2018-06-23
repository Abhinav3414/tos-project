package com.tos.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tos.model.Role;
import com.tos.model.User;
import com.tos.repository.RoleRepository;
import com.tos.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder userPasswordEncoder;
	
	/* search all users*/
	public List<User> getAllUser() {
		return userRepository.findAll();
	}


	/*to save a user */
	public User addUser(User user){
		Set<Role> userRole = new HashSet<>();
		userRole.add(roleRepository.findByName("EMPLOYEE"));
		user.setRoles(userRole);
		user.setActive(true);
		user.setPassword(userPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
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
