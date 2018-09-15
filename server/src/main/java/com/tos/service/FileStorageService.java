package com.tos.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tos.model.Customer;
import com.tos.model.Employee;
import com.tos.utility.FileStorageException;
import com.tos.utility.FileStorageProperties;
import com.tos.utility.MyFileNotFoundException;

@Service
public class FileStorageService {

	private final Path fileStorageLocationEmployees;

	private final Path fileStorageLocationCustomers;

	private final Path fileStorageLocation;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		this.fileStorageLocationEmployees = Paths.get(fileStorageProperties.getEmployeeUploadDir()).toAbsolutePath()
				.normalize();

		this.fileStorageLocationCustomers = Paths.get(fileStorageProperties.getCustomerUploadDir()).toAbsolutePath()
				.normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}

		try {
			Files.createDirectories(this.fileStorageLocationEmployees);
		} catch (Exception ex) {
			throw new FileStorageException(
					"Could not create the directory for employees where the uploaded files will be stored.", ex);
		}

		try {
			Files.createDirectories(this.fileStorageLocationCustomers);
		} catch (Exception ex) {
			throw new FileStorageException(
					"Could not create the directory for customers where the uploaded files will be stored.", ex);
		}
	}

	public String storeEmployeeProfilePic(MultipartFile file, String entityType, Long entityId) {

		Employee emp = employeeService.getEmployee(entityId);

		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// logic to save file Name as user's name
		String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		fileName = emp.getId() + ext;

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocationEmployees.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			emp.setImageName(fileName);
			employeeService.updateEmployee(emp, emp.getId());

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public String storeCustomerProfilePic(MultipartFile file, String entityType, Long entityId) {

		Customer cust = customerService.getCustomer(entityId);

		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// logic to save file Name as user's name
		String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		fileName = cust.getId() + ext;

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocationCustomers.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			cust.setImageName(fileName);
			customerService.updateCustomer(cust, cust.getId());

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public String storeFile(MultipartFile file) {

		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}
}