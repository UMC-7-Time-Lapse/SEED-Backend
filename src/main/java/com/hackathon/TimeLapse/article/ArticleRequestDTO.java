package com.hackathon.TimeLapse.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;


public class ArticleRequestDTO {

    @Getter
    public static class createArticleDTO {
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
        @NotNull
        private Long memberId;
        private List<String> imageList;
    }
}
