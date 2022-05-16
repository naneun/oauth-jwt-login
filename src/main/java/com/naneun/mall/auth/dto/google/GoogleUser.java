package com.naneun.mall.auth.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naneun.mall.auth.dto.OAuthAccessToken;
import com.naneun.mall.domain.entity.Member;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

import static com.naneun.mall.auth.dto.ResourceServer.GOOGLE;

@Getter
@ToString
public class GoogleUser {

    @JsonProperty("id")
    private String userId;

    @JsonProperty("email")
    private String email;

    @NotEmpty
    private OAuthAccessToken oAuthAccessToken;

    public void setAccessToken(OAuthAccessToken oAuthAccessToken) {
        this.oAuthAccessToken = oAuthAccessToken;
    }

    public Member toEntity() {
        return Member.builder()
                .socialId(userId)
                .jwtRefreshToken(null)
                .oauthAccessToken(oAuthAccessToken.getOAuthAccessToken())
                .oauthRefreshToken(oAuthAccessToken.getOAuthRefreshToken())
                .resourceServer(GOOGLE)
                .build();
    }
}
