package com.naneun.mall.auth.service;

import com.naneun.mall.auth.dto.*;
import com.naneun.mall.auth.dto.google.GoogleAccessToken;
import com.naneun.mall.auth.dto.google.GoogleAccessTokenRequest;
import com.naneun.mall.auth.dto.google.GoogleUser;
import com.naneun.mall.auth.properties.OAuthProperties;
import com.naneun.mall.domain.entity.Member;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("google")
public class GoogleOAuthService implements OAuthService {

    private final WebClient webClient;

    private final String clientId;
    private final String clientSecret;
    private final String accessTokenUri;
    private final String userUri;

    public GoogleOAuthService(WebClient webClient, OAuthProperties oAuthProperties) {
        this.webClient = webClient;
        this.clientId = oAuthProperties.getGoogleClientId();
        this.clientSecret = oAuthProperties.getGoogleClientSecret();
        this.accessTokenUri = oAuthProperties.getGoogleAccessTokenUri();
        this.userUri = oAuthProperties.getGoogleUserUri();
    }

    @Override
    public OAuthAccessToken requestAccessToken(String code) {

        GoogleAccessToken googleAccessToken = webClient.post()
                .uri(accessTokenUri)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(GoogleAccessTokenRequest.of(code, clientId, clientSecret))
                .retrieve()
                .bodyToMono(GoogleAccessToken.class)
                .blockOptional()
                .orElseThrow(RuntimeException::new);

        return googleAccessToken.toEntity();
    }

    @Override
    public Member requestUserInfo(OAuthAccessToken accessToken) {

        GoogleUser googleUser = webClient.get()
                .uri(userUri)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, accessToken.authorizationHeaderValue())
                .retrieve()
                .bodyToMono(GoogleUser.class)
                .blockOptional()
                .orElseThrow(RuntimeException::new);

        googleUser.setAccessToken(accessToken);

        return googleUser.toEntity();
    }
}
