package com.naneun.mall.auth.dto.google;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleAccessTokenRenewRequest {

    private final String clientId;
    private final String clientSecret;
    private final String refreshToken;
    private final String grantType;

    @Builder
    private GoogleAccessTokenRenewRequest(String clientId, String clientSecret, String refreshToken) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.refreshToken = refreshToken;
        this.grantType = "refresh_token";
    }

    public static GoogleAccessTokenRenewRequest of(String clientId, String clientSecret, String refreshToken) {
        return GoogleAccessTokenRenewRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .refreshToken(refreshToken)
                .build();
    }
}
