package com.naneun.mall.auth.service;

import com.naneun.mall.auth.dto.OAuthAccessToken;
import com.naneun.mall.domain.entity.Member;

public interface OAuthService {

    String CODE = "code";
    String CLIENT_ID = "client_id";
    String CLIENT_SECRET = "client_secret";

    String GITHUB_V3_JSON = "application/vnd.github.v3+json";

    OAuthAccessToken requestAccessToken(String code);

    Member requestUserInfo(OAuthAccessToken accessToken);
}
