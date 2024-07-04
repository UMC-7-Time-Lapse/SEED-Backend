package com.hackathon.TimeLapse.article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static ArticleResponseDTO.ArticleSummaryDTO toSummaryDTO(Article article) {
        String imageUrl = article.getImageList().isEmpty() ? null : article.getImageList().get(0).getImage_url();
        return new ArticleResponseDTO.ArticleSummaryDTO(
                article.getId(),
                imageUrl
        );
    }

    public static ArticleResponseDTO.ArticleDetailDTO toDetailDTO(Article article) {
        List<String> imageUrls = article.getImageList().stream()
                .map(image -> image.getImage_url())
                .collect(Collectors.toList());
        return new ArticleResponseDTO.ArticleDetailDTO(
                article.getId(),
                article.getTitle(),
                article.getDescription(),
                article.getLatitude(),
                article.getLongitude(),
                article.getStatus(),
                imageUrls
        );
    }

    public static ArticleResponseDTO.LatestArticleDTO toLatestArticleDTO(Article article) {
        return new ArticleResponseDTO.LatestArticleDTO(
                article.getLatitude(),
                article.getLongitude()
        );
    }

    public static ArticleResponseDTO.ArticleListDTO toArticleListDTO(List<Article> articles, Article latestArticle) {
        List<ArticleResponseDTO.ArticleSummaryDTO> articleDTOs = articles.stream()
                .map(ArticleConverter::toSummaryDTO)
                .collect(Collectors.toList());
        return new ArticleResponseDTO.ArticleListDTO(articleDTOs, toLatestArticleDTO(latestArticle));
    }
}
