package com.tos.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tos.service.FileStorageService;
import com.tos.utility.UploadFileResponse;

@RequestMapping("/tosfiles")
@RestController
public class FileController {

   // private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    
	

    
    @PostMapping("/uploadprofilepic/employees/{empId}")
    public UploadFileResponse uploadEmployeeProfilePic(@RequestParam("file") MultipartFile file , @PathVariable long empId) {
       
    	String fileName = fileStorageService.storeEmployeeProfilePic(file, "employees", empId);
        return finishUpload(file, fileName);
    }
    
    @PostMapping("/uploadprofilepic/customers/{custId}")
    public UploadFileResponse uploadCustomerProfilePic(@RequestParam("file") MultipartFile file , @PathVariable long custId) {
    	
        String fileName = fileStorageService.storeCustomerProfilePic(file, "customers", custId);
        return finishUpload(file, fileName);
    }
    
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    	
        String fileName = fileStorageService.storeFile(file);

        return finishUpload(file, fileName);
    }
    
    public UploadFileResponse finishUpload(MultipartFile file, String fileName) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        		.path("/tosfiles")
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
		/*
		 * // Load file as Resource Resource resource =
		 * fileStorageService.loadFileAsResource(fileName);
		 * 
		 * // Try to determine file's content type String contentType = null; try {
		 * contentType =
		 * request.getServletContext().getMimeType(resource.getFile().getAbsolutePath())
		 * ; } catch (IOException ex) { logger.info("Could not determine file type."); }
		 * 
		 * // Fallback to the default content type if type could not be determined
		 * if(contentType == null) { contentType = "application/octet-stream"; }
		 * 
		 * return ResponseEntity.ok()
		 * .contentType(MediaType.parseMediaType(contentType))
		 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
		 * resource.getFilename() + "\"") .body(resource);
		 */
    	return null;
    }
}
