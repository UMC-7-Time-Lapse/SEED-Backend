package com.hackathon.TimeLapse.article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hackathon.TimeLapse.apiPayload.ApiResponse;
import com.hackathon.TimeLapse.image.Image;
import com.hackathon.TimeLapse.image.ImageRequestDTO;
import com.hackathon.TimeLapse.image.ImageService;
import com.hackathon.TimeLapse.s3.S3Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
@RequestMapping("/api/article")
public class ArticleController {

	private final ArticleService articleService;
	private final S3Service s3Service;
	private final ImageService imageService;

	@PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ApiResponse<ArticleResponseDTO.createArticleResultDTO> createArticle(
		@RequestPart @Valid ArticleRequestDTO.createArticleDTO request,
		@RequestPart List<MultipartFile> files
	) throws IOException {
		long memberId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
		log.info("[*] memberId: " + memberId);
		System.out.println(files.get(0));
		List<String> uploadedFilesLinks = s3Service.uploadMultipleFiles(files);
		Article article = articleService.createArticle(memberId, request, uploadedFilesLinks);

		List<Image> images = new ArrayList<>();
		for (String url : uploadedFilesLinks) {
			Image image = Image.builder()
				.image_url(url)
				.article(article)
				.build();
			images.add(image);
		}
		imageService.addImages(article.getId(), new ImageRequestDTO.addImagesDTO(uploadedFilesLinks));

		return ApiResponse.onSuccess(ArticleConverter.toCreateReviewResultDTO(article));
	}

}
