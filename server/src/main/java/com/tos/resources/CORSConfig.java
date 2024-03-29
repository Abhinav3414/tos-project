package com.tos.resources;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * This class contains CORS configuration
 * it allows rest services to interact on cross platforms
 * 
 * @author Abhinav Gupta
 * @version 1.0 
 * @since 15-06-2018
 */

	@Configuration
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public class CORSConfig {

/*		@Bean
		public FilterRegistrationBean corsFilter() {
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowCredentials(true);
			config.addAllowedOrigin("*");
			config.addAllowedHeader("*");
			config.addAllowedMethod("*");
			source.registerCorsConfiguration("/**", config);
			FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
			bean.setOrder(0);
			return bean;
		}*/
		
		@Bean
	    public FilterRegistrationBean corsFilterRegistrationBean() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.applyPermitDefaultValues();
	        config.setAllowCredentials(true);
	        config.setAllowedOrigins(Arrays.asList("*"));
	        config.setAllowedHeaders(Arrays.asList("*"));
	        config.setAllowedMethods(Arrays.asList("*"));
	        config.setExposedHeaders(Arrays.asList("content-length"));
	        config.setMaxAge(3600L);
	        source.registerCorsConfiguration("/**", config);
	        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	        return bean;
	    }
		
	}
	

	

