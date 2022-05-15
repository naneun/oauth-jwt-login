package com.naneun.mall.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GitHubAccessToken {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    public OAuthAccessToken toEntity() {
        return OAuthAccessToken.of(accessToken, refreshToken, tokenType);
    }
}
