package com.tos.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	
	private String uploadDir;
	private String employeeUploadDir;
	private String customerUploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getEmployeeUploadDir() {
		return employeeUploadDir;
	}

	public void setEmployeeUploadDir(String employeeUploadDir) {
		this.employeeUploadDir = employeeUploadDir;
	}

	public String getCustomerUploadDir() {
		return customerUploadDir;
	}

	public void setCustomerUploadDir(String customerUploadDir) {
		this.customerUploadDir = customerUploadDir;
	}

}