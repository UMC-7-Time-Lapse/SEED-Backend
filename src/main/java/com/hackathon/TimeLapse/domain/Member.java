package com.hackathon.TimeLapse.domain;

import java.util.ArrayList;
import java.util.List;

import com.hackathon.TimeLapse.domain.common.BaseEntity;
import com.hackathon.TimeLapse.oauth.OAuthProvider;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String email;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;

    @OneToMany(
        mappedBy = "member",
        cascade = {CascadeType.ALL},
        fetch = FetchType.EAGER
    )
    private List<Article> articleList = new ArrayList<>();

    @Builder
    public Member(String email, String nickname, OAuthProvider oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }
}
