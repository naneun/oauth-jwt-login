package com.naneun.mall.auth.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ToString
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "oauth")
public class OAuthProperties {

    private final String githubClientId;
    private final String githubClientSecret;
    private final String githubAccessTokenUri;
    private final String githubUserUri;
}
