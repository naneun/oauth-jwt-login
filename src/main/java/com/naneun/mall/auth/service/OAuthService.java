package com.naneun.mall.auth.service;

import com.naneun.mall.auth.dto.common.OAuthAccessToken;
import com.naneun.mall.domain.entity.Member;

public interface OAuthService {

    default OAuthAccessToken requestAccessToken(String code) {
        return null;
    }

    default OAuthAccessToken renewAccessToken(Long userId) {
        return null;
    }

    default Member requestUserInfo(OAuthAccessToken accessToken) {
        return null;
    }
}
