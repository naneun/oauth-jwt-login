package com.naneun.mall.auth.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthAccessToken {

    private String oAuthAccessToken;
    private String oAuthRefreshToken;
    private String oAuthTokenType;

    @Builder
    private OAuthAccessToken(String oAuthAccessToken, String oAuthRefreshToken, String oAuthTokenType) {
        this.oAuthAccessToken = oAuthAccessToken;
        this.oAuthRefreshToken = oAuthRefreshToken;
        this.oAuthTokenType = oAuthTokenType;
    }

    public static OAuthAccessToken of(String oAuthAccessToken, String oAuthRefreshToken, String oAuthTokenType) {
        return OAuthAccessToken.builder()
                .oAuthAccessToken(oAuthAccessToken)
                .oAuthRefreshToken(oAuthRefreshToken)
                .oAuthTokenType(oAuthTokenType)
                .build();
    }

    public String authorizationHeaderValue() {
        return oAuthTokenType + " " + oAuthAccessToken;
    }
}
