package com.naneun.mall.auth.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.naneun.mall.auth.annotation.AccessTokenHeader;
import com.naneun.mall.auth.annotation.RefreshTokenHeader;
import com.naneun.mall.auth.dto.common.OAuthAccessToken;
import com.naneun.mall.auth.exception.jwt.JwtRefreshTokenException;
import com.naneun.mall.auth.provider.JwtTokenProvider;
import com.naneun.mall.auth.service.LoginService;
import com.naneun.mall.auth.service.OAuthService;
import com.naneun.mall.auth.service.RedisService;
import com.naneun.mall.domain.entity.Member;
import com.naneun.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import static com.naneun.mall.auth.utils.OAuthUtils.ACCESS_TOKEN;
import static com.naneun.mall.auth.utils.OAuthUtils.REFRESH_TOKEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final JwtTokenProvider jwtTokenProvider;

    private final LoginService loginService;
    private final MemberService memberService;

    private final RedisService redisService;
    private final Map<String, OAuthService> oAuthServices;

    @GetMapping("/{resource-server}/callback")
    public ResponseEntity<Void> login(@PathVariable("resource-server") String resourceServer,
                                String code, HttpServletResponse response) {
        OAuthService oAuthService = oAuthServices.get(resourceServer);
        OAuthAccessToken oAuthAccessToken = oAuthService.requestAccessToken(code);
        Member member = oAuthService.requestUserInfo(oAuthAccessToken);

        member = loginService.login(member);

        String jwtAccessToken = jwtTokenProvider.issueAccessToken(member);
        String jwtRefreshToken = jwtTokenProvider.issueRefreshToken(member);

        response.setHeader(ACCESS_TOKEN, jwtAccessToken);
        response.setHeader(REFRESH_TOKEN, jwtRefreshToken);

        redisService.saveJwtRefreshToken(member.getId().toString(), jwtRefreshToken);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/update/jwt-access-token")
    public void updateJwtAccessToken(@AccessTokenHeader String accessToken,
                                     @RefreshTokenHeader String refreshToken,
                                     HttpServletResponse response) {

        try {
            jwtTokenProvider.verifyToken(accessToken);
        } catch (TokenExpiredException e) {
            DecodedJWT decodedJWT = jwtTokenProvider.decodeToken(refreshToken);
            Long userId = jwtTokenProvider.getUserId(decodedJWT);
            validateRefreshToken(refreshToken, redisService.getJwtRefreshToken(userId.toString()));
            Member member = memberService.findMember(userId);
            response.setHeader(ACCESS_TOKEN, jwtTokenProvider.issueAccessToken(member));
        }
    }

    private void validateRefreshToken(String refreshToken1, String refreshToken2) {
        if (refreshToken1.equals(refreshToken2)) {
            throw new JwtRefreshTokenException();
        }
    }
}
