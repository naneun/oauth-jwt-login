package com.naneun.mall.auth.dto.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GitHubRefreshTokenRequest {

    private final String clientId;
    private final String clientSecret;
    private final String refreshToken;
    private final String grantType;

    @Builder
    private GitHubRefreshTokenRequest(String clientId, String clientSecret, String refreshToken, String grantType) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.refreshToken = refreshToken;
        this.grantType = grantType;
    }

    public static GitHubRefreshTokenRequest of(String clientId, String clientSecret) {
        return GitHubRefreshTokenRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .refreshToken(clientSecret)
                .grantType("refresh_token")
                .build();
    }
}
