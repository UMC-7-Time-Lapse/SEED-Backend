package com.hackathon.TimeLapse.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class S3Controller {

	private final S3Service s3Service;

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		return s3Service.uploadFile(file);
	}

	@GetMapping("/url")
	public String getFileUrl(@RequestParam("fileName") String fileName) {
		return s3Service.getFileUrl(fileName);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteFile(@RequestParam("fileName") String fileName) {
		s3Service.deleteFile(fileName);
		return ResponseEntity.ok("File deleted successfully");
	}
}