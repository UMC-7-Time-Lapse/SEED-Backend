package com.hackathon.TimeLapse.article;
import com.hackathon.TimeLapse.apiPayload.exception.MemberNotFoundException;
import com.hackathon.TimeLapse.domain.Image;
import com.hackathon.TimeLapse.member.MemberRepository;
import com.hackathon.TimeLapse.domain.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public ArticleService(ArticleRepository articleRepository, MemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Article createArticle(Long memberId, ArticleRequestDTO.createArticleDTO request) {
        Article article = ArticleConverter.toArticle(request);

        article.setMember(memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + memberId)));
        List<Image> images = new ArrayList<>();
        for (String imageUrl : request.getImageList()) {
            Image image = Image.builder()
                    .image_url(imageUrl)
                    .article(article)
                    .build();
            images.add(image);
        }
        article.setImageList(images);



        return articleRepository.save(article);
    }
}