package com.hackathon.TimeLapse.domain;

import com.hackathon.TimeLapse.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
