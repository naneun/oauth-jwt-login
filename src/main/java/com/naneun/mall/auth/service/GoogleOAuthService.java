package com.naneun.mall.auth.service;

import com.naneun.mall.auth.dto.common.OAuthAccessToken;
import com.naneun.mall.auth.dto.google.GoogleAccessToken;
import com.naneun.mall.auth.dto.google.GoogleAccessTokenRenewRequest;
import com.naneun.mall.auth.dto.google.GoogleAccessTokenRequest;
import com.naneun.mall.auth.dto.google.GoogleUser;
import com.naneun.mall.auth.exception.google.GoogleAccessTokenException;
import com.naneun.mall.auth.exception.google.GoogleApiException;
import com.naneun.mall.auth.exception.google.GoogleRefreshTokenException;
import com.naneun.mall.auth.properties.OAuthProperties;
import com.naneun.mall.domain.entity.Member;
import com.naneun.mall.repository.MemberRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("google")
public class GoogleOAuthService implements OAuthService {

    private final MemberRepository memberRepository;

    private final WebClient webClient;

    private final String clientId;
    private final String clientSecret;
    private final String accessTokenUri;
    private final String userUri;

    public GoogleOAuthService(WebClient webClient, MemberRepository memberRepository, OAuthProperties oAuthProperties) {
        this.webClient = webClient;
        this.memberRepository = memberRepository;
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
                .bodyValue(
                        GoogleAccessTokenRequest.of(
                                code,
                                clientId,
                                clientSecret
                        )
                )
                .retrieve()
                .bodyToMono(GoogleAccessToken.class)
                .blockOptional()
                .orElseThrow(GoogleAccessTokenException::new);

        return googleAccessToken.toEntity();
    }

    /**
     * ?????? access-token ??? ????????? ???????????? ????????????.
     * ????????? access-token ?????? ????????? ?????????????
     *
     * 1. ????????? ????????? access-token ??? ?????????????????? ????????? ??????????!
     * 2. ????????? ?????????, batch ??? ???????????? refresh-token ??? ???????????? access-token ??? ????????????????!
     * 
     * @param userId
     * @return
     */
    @Override
    public OAuthAccessToken renewAccessToken(Long userId) {

        GoogleAccessToken googleAccessToken = webClient.post()
                .uri(accessTokenUri)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(
                        GoogleAccessTokenRenewRequest.of(
                                clientId,
                                clientSecret,
                                memberRepository.findOAuthRefreshTokenById(userId)
                        )
                )
                .retrieve()
                .bodyToMono(GoogleAccessToken.class)
                .blockOptional()
                .orElseThrow(GoogleRefreshTokenException::new);

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
                .orElseThrow(GoogleApiException::new);

        return googleUser.toEntity(accessToken);
    }
}
