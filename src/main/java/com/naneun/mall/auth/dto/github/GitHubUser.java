package com.naneun.mall.auth.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naneun.mall.auth.dto.common.OAuthAccessToken;
import com.naneun.mall.domain.entity.Member;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

import static com.naneun.mall.auth.dto.common.ResourceServer.GITHUB;

@Getter
@ToString
public class GitHubUser {

    @JsonProperty("login")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    public Member toEntity(OAuthAccessToken oAuthAccessToken) {
        return Member.builder()
                .socialId(userId)
                .name(name)
                .oauthAccessToken(oAuthAccessToken.getOAuthAccessToken())
                .oauthRefreshToken(oAuthAccessToken.getOAuthRefreshToken())
                .resourceServer(GITHUB)
                .build();
    }
}
