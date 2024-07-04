package com.hackathon.TimeLapse.article;

import com.hackathon.TimeLapse.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Article, Long> {
}