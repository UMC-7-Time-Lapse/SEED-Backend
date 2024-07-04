package com.hackathon.TimeLapse.article;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponseDTO {

    @Getter
    @AllArgsConstructor
    public static class SimpleArticleListDTO {
        private List<SimpleArticleDTO> articles;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createArticleResultDTO{
        Long articleId;
        LocalDateTime createdAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleSummaryDTO {
        private Long id;
        private String imageUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleDetailDTO {
        private Long id;
        private String title;
        private String description;
        private Double latitude;
        private Double longitude;
        private Long status;
        private List<String> imageUrls;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleListDTO {
        private List<ArticleSummaryDTO> articles;
        private LatestArticleDTO latestArticle;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LatestArticleDTO {
        private Double latitude;
        private Double longitude;
    }

}
