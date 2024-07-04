package com.hackathon.TimeLapse.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.member.id = :memberId ORDER BY a.createdAt DESC")
    List<Article> findTopByMemberIdOrderByCreatedAtDesc(@Param("memberId") Long memberId);

    List<Article> findByMemberId(Long memberId);
}
