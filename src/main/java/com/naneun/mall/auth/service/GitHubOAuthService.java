package com.naneun.mall.auth.service;

import com.naneun.mall.auth.dto.common.OAuthAccessToken;
import com.naneun.mall.auth.dto.github.GitHubAccessToken;
import com.naneun.mall.auth.dto.github.GitHubAccessTokenRequest;
import com.naneun.mall.auth.dto.github.GitHubAccessTokenRenewRequest;
import com.naneun.mall.auth.dto.github.GitHubUser;
import com.naneun.mall.auth.properties.OAuthProperties;
import com.naneun.mall.domain.entity.Member;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("github")
public class GitHubOAuthService implements OAuthService {

    private final WebClient webClient;

    private final String clientId;
    private final String clientSecret;
    private final String accessTokenUri;
    private final String userUri;

    public GitHubOAuthService(WebClient webClient, OAuthProperties oAuthProperties) {
        this.webClient = webClient;
        this.clientId = oAuthProperties.getGithubClientId();
        this.clientSecret = oAuthProperties.getGithubClientSecret();
        this.accessTokenUri = oAuthProperties.getGithubAccessTokenUri();
        this.userUri = oAuthProperties.getGithubUserUri();
    }

    @Override
    public OAuthAccessToken requestAccessToken(String code) {

        GitHubAccessToken gitHubAccessToken = webClient.post()
                .uri(accessTokenUri)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(
                        GitHubAccessTokenRequest.of(
                                code,
                                clientId,
                                clientSecret
                        )
                )
                .retrieve()
                .bodyToMono(GitHubAccessToken.class)
                .blockOptional()
                .orElseThrow(RuntimeException::new);

        if (gitHubAccessToken.hasRefreshToken()) {
            return renewAccessToken(0L);
        }

        return gitHubAccessToken.toEntity();
    }

    /**
     * The response parameters expires_in, refresh_token, and refresh_token_expires_in are
     * only returned when you enable expiring user-to-server access tokens.
     *
     * @param userId
     * @return
     */
    @Override
    public OAuthAccessToken renewAccessToken(Long userId) {

        GitHubAccessToken gitHubAccessToken = webClient.post()
                .uri(accessTokenUri)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(
                        GitHubAccessTokenRenewRequest.of(
                                clientId,
                                clientSecret
                        )
                )
                .retrieve()
                .bodyToMono(GitHubAccessToken.class)
                .blockOptional()
                .orElseThrow(RuntimeException::new);

        return gitHubAccessToken.toEntity();
    }

    @Override
    public Member requestUserInfo(OAuthAccessToken accessToken) {

        GitHubUser gitHubUser = webClient.get()
                .uri(userUri)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, accessToken.authorizationHeaderValue())
                .retrieve()
                .bodyToMono(GitHubUser.class)
                .blockOptional()
                .orElseThrow(RuntimeException::new);

        return gitHubUser.toEntity(accessToken);
    }
}
