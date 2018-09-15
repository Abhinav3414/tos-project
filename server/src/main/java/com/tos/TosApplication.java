package com.tos;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tos.model.CustomUserDetails;
import com.tos.model.Role;
import com.tos.model.User;
import com.tos.repository.RoleRepository;
import com.tos.repository.UserRepository;
import com.tos.utility.FileStorageProperties;

/**
 * This is the main class of Spring boot application contains run method which
 * starts spring container
 * 
 * @author Abhinav Gupta
 * @version 1.0
 * @since 15-06-2018
 */

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class TosApplication {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder userPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(TosApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {

		if(roleRepository.count() == 0) {
			roleRepository.save((new Role("MANAGER")));
			roleRepository.save((new Role("EMPLOYEE")));
			roleRepository.save((new Role("GUEST")));
			roleRepository.save((new Role("CUSTOMER")));
		}
		
		if (repo.count() == 0) {
			User user = new User();
			Set<Role> userRole = new HashSet<>();
			userRole.add(new Role("ADMIN"));
			user.setUsername("tosadminuser");
			user.setRoles(userRole);
			user.setActive(true);
			user.setPassword(userPasswordEncoder.encode("tosadminuser"));
			repo.save(user);
		}

		builder.userDetailsService(s -> {
			Optional<User> u = repo.findByUsername(s);
			User us = u.get();
			return new CustomUserDetails(us);

		});
	}

}
