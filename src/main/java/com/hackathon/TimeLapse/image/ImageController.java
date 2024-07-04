package com.hackathon.TimeLapse.image;

import com.hackathon.TimeLapse.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/")
    public ApiResponse<ImageResponseDTO.addImageResultDTO> addImages(@RequestBody @Valid ImageRequestDTO.addImagesDTO request,
                                                                     @RequestHeader("articleId") Long articleId) {
        List<Image> images = imageService.addImages(articleId, request);
        return ApiResponse.onSuccess(ImageConverter.toAddImageResultDTO(images));
    }
}
