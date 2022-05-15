package com.naneun.mall.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GitHubAccessTokenRequest {

    private final String code;
    private final String clientId;
    private final String clientSecret;

    @Builder
    private GitHubAccessTokenRequest(String code, String clientId, String clientSecret) {
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public static GitHubAccessTokenRequest of(String code, String clientId, String clientSecret) {
        return GitHubAccessTokenRequest.builder()
                .code(code)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }
}
