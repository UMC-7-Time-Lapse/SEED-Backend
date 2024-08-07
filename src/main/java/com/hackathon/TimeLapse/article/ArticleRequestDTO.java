package com.hackathon.TimeLapse.article;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    public static class createArticleDTO {
        @NotNull
        private Long memberId;
        @NotNull
        private Double latitude;
        @NotNull
        private Double longitude;
        @NotBlank
        private String title;
        @NotBlank
        private String description;
        @NotNull
        private Long status;
    }
}
