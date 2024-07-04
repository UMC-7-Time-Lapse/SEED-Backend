package com.hackathon.TimeLapse.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ArticleResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createArticleResultDTO{
        Long articleId;
        LocalDateTime createdAt;
    }

}
