package com.tos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * This class is Resource server which validates requests which have OAuth2 token
 * it grants them access to the specified urls with role access
 * 
 * @author Abhinav Gupta
 * @version 1.0 
 * @since 15-06-2018
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
        .anonymous()
        .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS,"**").permitAll()
            .antMatchers("/tos/**").hasRole("ADMIN")
            ;
        
	}
	
/*	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authenticationManager)
		.userDetailsService(customUserDetailsService);
	}*/


}
