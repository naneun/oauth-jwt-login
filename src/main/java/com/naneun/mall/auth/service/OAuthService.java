package com.naneun.mall.auth.service;

import com.naneun.mall.auth.dto.OAuthAccessToken;
import com.naneun.mall.domain.entity.Member;

public interface OAuthService {

    default OAuthAccessToken requestAccessToken(String code) {
        return null;
    }

    default Member requestUserInfo(OAuthAccessToken accessToken) {
        return null;
    }
}
