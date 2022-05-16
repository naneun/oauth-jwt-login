package com.naneun.mall.auth.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naneun.mall.auth.dto.common.OAuthAccessToken;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

@Getter
@ToString
public class GoogleAccessToken {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private String expiresIn;

    public OAuthAccessToken toEntity() {
        return OAuthAccessToken.of(accessToken, refreshToken, tokenType);
    }

    public boolean hasRefreshToken() {
        return Strings.isNotBlank(refreshToken);
    }
}
