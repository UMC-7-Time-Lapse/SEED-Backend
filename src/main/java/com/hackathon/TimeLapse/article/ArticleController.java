package com.hackathon.TimeLapse.article;

import com.hackathon.TimeLapse.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/")
    public ApiResponse<ArticleResponseDTO.createArticleResultDTO> createArticle(@RequestBody @Valid ArticleRequestDTO.createArticleDTO request,
                                                                                   @RequestParam(name = "memberId") Long memberId) {
        Article article = articleService.createArticle(memberId, request);
        return ApiResponse.onSuccess(ArticleConverter.toCreateReviewResultDTO(article));
    }

}
