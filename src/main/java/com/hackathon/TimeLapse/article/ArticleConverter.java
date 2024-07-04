package com.hackathon.TimeLapse.article;

import com.hackathon.TimeLapse.domain.Article;

import java.time.LocalDateTime;

public class ArticleConverter {
    public static Article toArticle(ArticleRequestDTO.createArticleDTO request){
        return Article.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .title(request.getTitle())
                .description(request.getDescription())
                .status(0L)
                .build();
    }

    public static ArticleResponseDTO.createArticleResultDTO toCreateReviewResultDTO(Article article){
        return ArticleResponseDTO.createArticleResultDTO.builder()
                .articleId(article.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
