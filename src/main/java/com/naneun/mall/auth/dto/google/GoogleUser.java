package com.naneun.mall.auth.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naneun.mall.auth.dto.common.OAuthAccessToken;
import com.naneun.mall.domain.entity.Member;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

import static com.naneun.mall.auth.dto.common.ResourceServer.GOOGLE;

@Getter
@ToString
public class GoogleUser {

    @JsonProperty("id")
    private String userId;

    @JsonProperty("email")
    private String email;

    public Member toEntity(OAuthAccessToken oAuthAccessToken) {
        return Member.builder()
                .socialId(userId)
                .oauthAccessToken(oAuthAccessToken.getOAuthAccessToken())
                .oauthRefreshToken(oAuthAccessToken.getOAuthRefreshToken())
                .resourceServer(GOOGLE)
                .build();
    }
}
