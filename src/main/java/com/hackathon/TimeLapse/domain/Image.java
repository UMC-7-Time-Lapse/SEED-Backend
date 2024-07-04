package com.hackathon.TimeLapse.domain;

import com.hackathon.TimeLapse.domain.common.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

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
