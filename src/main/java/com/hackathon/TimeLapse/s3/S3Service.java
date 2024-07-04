package com.hackathon.TimeLapse.s3;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3Service {

	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			String fileUrl = "https://" + bucket + "/test" + fileName;
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(file.getContentType());
			metadata.setContentLength(file.getSize());
			amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
			return ResponseEntity.ok(fileUrl);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	public ResponseEntity<List<String>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		List<String> fileUrls = new ArrayList<>();
		for (MultipartFile file : files) {
			try {
				String fileName = file.getOriginalFilename();
				String fileUrl = "https://" + bucket + "/test" + fileName;
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(file.getContentType());
				metadata.setContentLength(file.getSize());
				amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
				fileUrls.add(fileUrl);
			} catch (IOException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		return ResponseEntity.ok(fileUrls);
	}

	public void deleteFile(String fileName) {
		amazonS3Client.deleteObject(bucket, fileName);
	}

	public String getFileUrl(String fileName) {
		LocalDateTime expiration = LocalDateTime.now().plusHours(1);
		Date expirationDate = Date.from(expiration.atZone(ZoneOffset.systemDefault()).toInstant());

		GeneratePresignedUrlRequest generatePresignedUrlRequest =
			new GeneratePresignedUrlRequest(bucket, fileName)
				.withMethod(HttpMethod.GET)
				.withExpiration(expirationDate);

		URL url = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
		return url.toString();
	}
}
