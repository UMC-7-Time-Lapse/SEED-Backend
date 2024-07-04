package com.hackathon.TimeLapse.image;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ImageRequestDTO {
    @Getter
    @AllArgsConstructor
    public static class addImagesDTO {

        private List<String> imageUrlList;
    }
}
