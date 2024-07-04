package com.hackathon.TimeLapse.article;
import java.util.List;

import com.hackathon.TimeLapse.apiPayload.exception.MemberNotFoundException;
import com.hackathon.TimeLapse.image.ImageService;
import com.hackathon.TimeLapse.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public ArticleService(ArticleRepository articleRepository, MemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Article createArticle(Long memberId, ArticleRequestDTO.createArticleDTO request,
        List<String> uploadedFilesLinks) {
        Article article = ArticleConverter.toArticle(request);

        article.setMember(memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + memberId)));

        return articleRepository.save(article);
    }
}
