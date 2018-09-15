package com.tos.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tos.model.Role;
import com.tos.model.User;
import com.tos.model.UserResponse;
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

	public boolean isCurrentUserAdmin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> currentUser = userRepository.findByUsername(auth.getName());

		if (currentUser.isPresent()) {
			User user = currentUser.get();

			if (user.getRoles().stream().filter(r -> r.getName().equals("ADMIN")).count() > 0) {
				return true;
			}
		}
		return false;
	}

	/* search all users */
	public List<User> getAllUser() {

		if (isCurrentUserAdmin() == true) {
			return userRepository.findAll();
		}
		return null;

	}

	/* to save a user */
	public User addUser(User user) {
		Optional<User> existingUserOption = userRepository.findByUsername(user.getUsername());
		if (existingUserOption.isPresent()) {
			return null;
		}
		Set<Role> userRole = new HashSet<>();
		userRole.add(roleRepository.findByName("EMPLOYEE"));
		user.setRoles(userRole);
		user.setActive(true);
		user.setPassword(userPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	/* get a user by id */
	public User getUser(Long userId) {
		return userRepository.findOne(userId);
	}

	public UserResponse getCurrentUser(String username) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Optional<User> currentUser = userRepository.findByUsername(auth.getName());
		Optional<User> requestingUserOption = userRepository.findByUsername(username);

		if (currentUser.isPresent() && requestingUserOption.isPresent()
				&& requestingUserOption.get().getUsername().equals(currentUser.get().getUsername())) {

			User user = requestingUserOption.get();
			UserResponse userResponse = new UserResponse(user.getId(), user.getUsername(), user.getEmail(),
					user.getRoles(), user.getImageName());
			return userResponse;
		}
		return null;
	}

	/* to update a user */
	public User updateUser(User userData, long id) {
		if (userRepository.findOne(id) == null) {
			return null;
		}
		return userRepository.save(userData);
	}

	/* to update Roles of a user */
	public boolean updateUserRoles(User user, Long userId) {

		if (userRepository.findOne(userId) == null) {
			return false;
		}
		if (isCurrentUserAdmin() == true) {
			User currentUser = userRepository.findOne(userId);
			Set<Role> userRoles = currentUser.getRoles();
			userRoles.clear();
			currentUser.setRoles(userRoles);
			userRepository.save(currentUser);

			Set<Role> userRole = new HashSet<>();
			for (Role role : user.getRoles()) {
				userRole.add(roleRepository.findByName(role.getName()));
			}

			currentUser.setRoles(userRole);
			
			userRepository.save(currentUser);
		}
		return true;
	}

	/* delete a user */
	public boolean deleteUser(Long userId) {
		if (userRepository.findOne(userId) == null) {
			return false;
		}
		if (isCurrentUserAdmin() == true) {
			User user = userRepository.findOne(userId);
			Set<Role> roles = user.getRoles();
			roles.clear();
			user.setRoles(roles);
			userRepository.save(user);
			userRepository.delete(userId);

		}

		return true;
	}

}
