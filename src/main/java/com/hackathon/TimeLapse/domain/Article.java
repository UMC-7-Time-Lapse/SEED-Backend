package com.hackathon.TimeLapse.domain;

import com.hackathon.TimeLapse.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            nullable = false,
            length = 40
    )
    private String title;

    private Double latitude;

    private Double longitude;

    @Column(
            nullable = false,
            length = 400
    )
    private String description;

    private Long status;

    @ManyToOne
    @JoinColumn(
            name="member_id"
    )
    private Member member;

    @OneToMany(
            mappedBy = "article",
            cascade = {CascadeType.ALL}
    )
    @Builder.Default
    private List<Image> imageList = new ArrayList<>();
}
