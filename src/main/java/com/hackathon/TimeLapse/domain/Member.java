package com.hackathon.TimeLapse.domain;

import com.hackathon.TimeLapse.domain.common.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @OneToMany(
            mappedBy = "member",
            cascade = {CascadeType.ALL}
    )
    private List<Article> articleList = new ArrayList<>();
}
