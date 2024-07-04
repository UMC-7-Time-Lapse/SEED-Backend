package com.hackathon.TimeLapse.image;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ImageConverter {

    public static ImageResponseDTO.addImageResultDTO toAddImageResultDTO(List<Image> images){
        List<String> imageUrlList = images.stream()
                .map(Image::getImage_url)
                .collect(Collectors.toList());
        return new ImageResponseDTO.addImageResultDTO(imageUrlList);
    }
}
