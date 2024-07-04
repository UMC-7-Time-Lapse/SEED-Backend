package com.hackathon.TimeLapse.image;
import com.hackathon.TimeLapse.apiPayload.exception.ArticleNotFoundException;
import com.hackathon.TimeLapse.article.Article;
import com.hackathon.TimeLapse.article.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ArticleRepository articleRepository;

    public ImageService(ImageRepository imageRepository, ArticleRepository articleRepository) {
        this.imageRepository = imageRepository;
        this.articleRepository = articleRepository;
    }

    @Transactional
    public List<Image> addImages(Long articleId, ImageRequestDTO.addImagesDTO request) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found with id: " + articleId));

        List<Image> images = new ArrayList<>();
        for (String imageUrl : request.getImageUrlList()) {
            Image image = Image.builder()
                    .image_url(imageUrl)
                    .article(article)
                    .build();
            images.add(image);
        }

        return imageRepository.saveAll(images);
    }
}