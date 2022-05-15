package com.naneun.mall.auth.service;

import com.naneun.mall.auth.dto.OAuthAccessToken;
import com.naneun.mall.domain.entity.Member;

public interface OAuthService {

    OAuthAccessToken requestAccessToken(String code);
    Member requestUserInfo(OAuthAccessToken accessToken);
}
