package com.naneun.mall.auth.dto.google;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleAccessTokenRequest {

    private final String code;
    private final String clientId;
    private final String clientSecret;
    private final String grantType;
    private final String redirectUri;

    @Builder
    private GoogleAccessTokenRequest(String code, String clientId, String clientSecret) {
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = "authorization_code";
        this.redirectUri = "http://localhost:8080/login/google/callback";
    }

    public static GoogleAccessTokenRequest of(String code, String clientId, String clientSecret) {
        return GoogleAccessTokenRequest.builder()
                .code(code)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }
}
