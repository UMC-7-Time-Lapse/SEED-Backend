package com.hackathon.TimeLapse.image;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class ImageRequestDTO {
    @Getter
    public static class addImagesDTO {

        private List<String> imageUrlList;
    }
}
