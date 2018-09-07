package com.tos.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tos.model.User;
import com.tos.model.UserResponse;
import com.tos.service.UserService;

@RestController
@RequestMapping("/tosuser")
public class UserController {

	@Autowired
	private UserService userService;

	// TODO: Not safe. Delete Get mapping on Users

	@GetMapping("/users")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable long id) {
		return userService.getUser(id);
	}
	
	@GetMapping("/currentuser/{username}")
	public UserResponse getCurrentUser(@PathVariable String username) {
		return userService.getCurrentUser(username);
	}

	@PostMapping("/users")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		User customUser = userService.addUser(user);
		if (customUser == null) {
			return new ResponseEntity<String>("UserName already exists",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(customUser, HttpStatus.OK);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Void> updateUser(@Valid @RequestBody User user, @PathVariable Long id) {
		if (userService.updateUser(user, id) == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}/roles")
	public ResponseEntity<Void> updateUserRoles(@Valid @RequestBody User user, @PathVariable Long id) {
		if (userService.updateUserRoles(user, id) == false) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id) {
		if (userService.deleteUser(id) == false) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
