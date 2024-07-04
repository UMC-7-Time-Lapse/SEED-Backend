package com.hackathon.TimeLapse.image;

import com.hackathon.TimeLapse.article.Article;
import com.hackathon.TimeLapse.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            nullable = false,
            length = 200
    )
    private String image_url;

    @ManyToOne
    @JoinColumn(
            name="article_id"
    )
    private Article article;
}
