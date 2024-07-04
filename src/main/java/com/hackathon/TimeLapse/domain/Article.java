package com.hackathon.TimeLapse.domain;

import com.hackathon.TimeLapse.domain.common.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            nullable = false,
            length = 200
    )
    private String location;

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
    private List<Image> imageList = new ArrayList<>();
}
