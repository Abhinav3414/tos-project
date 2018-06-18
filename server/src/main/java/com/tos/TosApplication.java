package com.tos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tos.model.CustomUserDetails;
import com.tos.model.Role;
import com.tos.model.Roles;
import com.tos.model.User;
import com.tos.repository.UserRepository;

/**
 * This is the main class of Spring boot application
 * contains run method which starts spring container
 * 
 * @author Abhinav Gupta
 * @version 1.0 
 * @since 15-06-2018
 */

@SpringBootApplication
@EnableJpaAuditing
public class TosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TosApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
		
	/*	if(repo.count() == 0) {
			repo.save(new User("tosadminuser","tosadminuser@comakeit.com","tosadminuser", Arrays.asList(new Role(Roles.ADMIN.toString()))));
		}
		builder.userDetailsService(s -> new CustomUserDetails(repo.findByUsername(s)));
*/
	}

	
}
