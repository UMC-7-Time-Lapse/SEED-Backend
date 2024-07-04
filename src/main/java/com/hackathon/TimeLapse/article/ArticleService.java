package com.hackathon.TimeLapse.article;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.hackathon.TimeLapse.apiPayload.exception.ArticleNotFoundException;
import com.hackathon.TimeLapse.apiPayload.exception.MemberNotFoundException;
import com.hackathon.TimeLapse.member.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    @Transactional
    public ArticleResponseDTO.ArticleListDTO getAllArticles(Long memberId) {
        List<Article> articles = articleRepository.findAll();
        articles.forEach(article -> {
            article.getImageList().size(); // 초기화
        });

        List<Article> latestArticles = articleRepository.findTopByMemberIdOrderByCreatedAtDesc(memberId);
        Article latestArticle = latestArticles.isEmpty() ? null : latestArticles.get(0);

        return ArticleConverter.toArticleListDTO(articles, latestArticle);
    }

    @Transactional
    public ArticleResponseDTO.SimpleArticleListDTO getMemberArticles(Long memberId) {
        List<Article> articles = articleRepository.findByMemberId(memberId);
        articles.forEach(article -> {
            article.getImageList().size(); // Initialize lazy-loaded collections
        });

        List<SimpleArticleDTO> simpleArticleDTOs = articles.stream()
            .map(article -> new SimpleArticleDTO(
                article.getLatitude(),
                article.getLongitude(),
                article.getMember().getId(),
                article.getTitle(),
                article.getDescription()))
            .collect(Collectors.toList());

        return new ArticleResponseDTO.SimpleArticleListDTO(simpleArticleDTOs);
    }

    @Transactional
    public ArticleResponseDTO.ArticleDetailDTO getArticleDetail(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found with id: " + articleId));
        return ArticleConverter.toDetailDTO(article);
    }
}
