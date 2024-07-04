package com.hackathon.TimeLapse.image;

import com.hackathon.TimeLapse.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}