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

        return articleRepository.save(article);
    }
}