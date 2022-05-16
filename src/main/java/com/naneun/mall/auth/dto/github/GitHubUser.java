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

    @NotEmpty
    private OAuthAccessToken oAuthAccessToken;

    public void setAccessToken(OAuthAccessToken oAuthAccessToken) {
        this.oAuthAccessToken = oAuthAccessToken;
    }

    public Member toEntity() {
        return Member.builder()
                .socialId(userId)
                .name(name)
                .jwtRefreshToken(null)
                .oauthAccessToken(oAuthAccessToken.getOAuthAccessToken())
                .oauthRefreshToken(oAuthAccessToken.getOAuthRefreshToken())
                .resourceServer(GITHUB)
                .build();
    }
}
