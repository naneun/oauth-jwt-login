package com.naneun.mall.auth.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.naneun.mall.auth.annotation.AccessTokenParam;
import com.naneun.mall.auth.annotation.RefreshTokenParam;
import com.naneun.mall.auth.dto.LoginResponse;
import com.naneun.mall.auth.dto.OAuthAccessToken;
import com.naneun.mall.auth.provider.JwtTokenProvider;
import com.naneun.mall.auth.service.LoginService;
import com.naneun.mall.auth.service.OAuthService;
import com.naneun.mall.domain.entity.Member;
import com.naneun.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import static com.naneun.mall.auth.utils.OAuthUtils.ACCESS_TOKEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final JwtTokenProvider jwtTokenProvider;
    private final LoginService loginService;
    private final MemberService memberService;
    private final Map<String, OAuthService> oAuthServices;

    @GetMapping("/{resource-server}/callback")
    public LoginResponse login(@PathVariable("resource-server") String resourceServer, String code) {
        OAuthService oAuthService = oAuthServices.get(resourceServer);
        OAuthAccessToken oAuthAccessToken = oAuthService.requestAccessToken(code);
        Member member = oAuthService.requestUserInfo(oAuthAccessToken);
        member = memberService.updateMember(member);

        String jwtAccessToken = jwtTokenProvider.issueAccessToken(member);
        String jwtRefreshToken = jwtTokenProvider.issueRefreshToken(member);
        member.changeJwtRefreshToken(jwtRefreshToken);
        member = memberService.updateMember(member);

        return LoginResponse.of(member.getSocialId(), jwtAccessToken, jwtRefreshToken);
    }

    @GetMapping("/update/access-token")
    public void updateJwtAccessToken(@AccessTokenParam String accessToken,
                                     @RefreshTokenParam String refreshToken,
                                     HttpServletResponse response) {

        try {
            jwtTokenProvider.verifyToken(accessToken);
        } catch (TokenExpiredException e) {
            DecodedJWT decodedJWT = jwtTokenProvider.decodeToken(refreshToken);
            Long userId = jwtTokenProvider.getUserId(decodedJWT);
            Member member = loginService.findUserByIdAndRefreshToken(userId, refreshToken);
            response.setHeader(ACCESS_TOKEN, jwtTokenProvider.issueAccessToken(member));
        }
    }
}
