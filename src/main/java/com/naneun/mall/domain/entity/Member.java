package com.naneun.mall.domain.entity;

import com.naneun.mall.auth.dto.ResourceServer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = "orderSheets")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String socialId;

    private String name;

    @Column(length = 1000)
    private String jwtRefreshToken;

    @Column(length = 1000)
    private String oauthAccessToken;

    @Column(length = 1000)
    private String oauthRefreshToken;

    private ResourceServer resourceServer;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<OrderSheet> orderSheets;

    @Builder
    private Member(Long id, String socialId, String name, String jwtRefreshToken, String oauthAccessToken,
                   String oauthRefreshToken, ResourceServer resourceServer) {

        this.id = id;
        this.socialId = socialId;
        this.name = name;
        this.jwtRefreshToken = jwtRefreshToken;
        this.oauthAccessToken = oauthAccessToken;
        this.oauthRefreshToken = oauthRefreshToken;
        this.resourceServer = resourceServer;
        this.orderSheets = new ArrayList<>();
    }

    public void changeJwtRefreshToken(String jwtRefreshToken) {
        this.jwtRefreshToken = jwtRefreshToken;
    }

    public void changeOauthAccessToken(String oauthAccessToken) {
        this.oauthAccessToken = oauthAccessToken;
    }

    public void changeOauthRefreshToken(String oauthRefreshToken) {
        this.oauthRefreshToken = oauthRefreshToken;
    }
}
