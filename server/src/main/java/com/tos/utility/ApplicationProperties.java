package com.tos.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class creates Java objects from property files present in src/main/resources folder
 * 
 * 
 * @author Abhinav Gupta
 * @version 1.0 
 * @since 15-06-2018
 */

@Component
public class ApplicationProperties {

	@Value("${tos.appBaseUrl.url}")
	private String appBaseUrl;
	
	@Value("${tos.baseResource.url}")
	private String baseResourceUrl;
	
	@Value("${tos.baseResourceUser.url}")
	private String baseResourceUserUrl;
	
	@Value("${tos.tokenApi.url}")
	private String tokenApiUrl;

	@Value("${tos.property.url}")
	private String propertyUrl;

	
	public String getAppBaseUrl() {
		return this.appBaseUrl;
	}
	
	public String getBaseResourceUrl() {
		return this.baseResourceUrl;
	}
	
	public String getBaseResourceUsersUrl() {
		return this.baseResourceUserUrl;
	}

	public String getTokenApiUrl() {
		return this.tokenApiUrl;
	}
	
	public String getPropertyUrl() {
		return this.propertyUrl;
	}

}
