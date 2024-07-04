package com.hackathon.TimeLapse.article;

import com.hackathon.TimeLapse.apiPayload.ApiResponse;
import com.hackathon.TimeLapse.domain.Article;
import com.hackathon.TimeLapse.s3.S3Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class ArticleController {
    private final ArticleService articleService;

    private final S3Service s3Service;

    @PostMapping("/article")
    public ApiResponse<ArticleResponseDTO.createArticleResultDTO> createArticle(@RequestBody @Valid ArticleRequestDTO.createArticleDTO request,
                                                                                   @RequestParam(name = "memberId") Long memberId) {
        Article article = articleService.createArticle(memberId, request);
        return ApiResponse.onSuccess(ArticleConverter.toCreateReviewResultDTO(article));
    }
}
