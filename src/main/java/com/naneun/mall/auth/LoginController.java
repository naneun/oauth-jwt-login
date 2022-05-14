package com.naneun.mall.auth;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.naneun.mall.domain.entity.Member;
import com.naneun.mall.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static com.naneun.mall.auth.OAuthUtils.ACCESS_TOKEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final JwtTokenProvider jwtTokenProvider;
    private final LoginService loginService;

    @GetMapping("/oauth/github")
    public void loginGithub() {

    }

    @GetMapping("/update/access-token")
    public void updateAccessToken(@AccessToken String accessToken, @RefreshToken String refreshToken,
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
