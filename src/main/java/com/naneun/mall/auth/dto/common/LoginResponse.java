package com.naneun.mall.auth.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String socialId;
    private String jwtAccessToken;
    private String jwtRefreshToken;

    @Builder
    private LoginResponse(String socialId, String jwtAccessToken, String jwtRefreshToken) {
        this.socialId = socialId;
        this.jwtAccessToken = jwtAccessToken;
        this.jwtRefreshToken = jwtRefreshToken;
    }

    public static LoginResponse of(String socialId, String jwtAccessToken, String jwtRefreshToken) {
        return LoginResponse.builder()
                .socialId(socialId)
                .jwtAccessToken(jwtAccessToken)
                .jwtRefreshToken(jwtRefreshToken)
                .build();
    }
}
