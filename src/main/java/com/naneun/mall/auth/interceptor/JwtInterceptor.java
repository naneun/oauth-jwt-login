package com.naneun.mall.auth.interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.naneun.mall.auth.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.naneun.mall.auth.utils.OAuthUtils.BEARER;
import static com.naneun.mall.auth.utils.OAuthUtils.SOCIAL_ID;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return verifyJwtToken(request, response);
    }

    private boolean verifyJwtToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!Strings.isNotBlank(authorizationHeader) || !authorizationHeader.startsWith(BEARER)) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        try {
            String accessToken = authorizationHeader.replaceFirst(BEARER, Strings.EMPTY).trim();
            DecodedJWT decodedJWT = jwtTokenProvider.verifyToken(accessToken);
            request.setAttribute(SOCIAL_ID, jwtTokenProvider.getUserId(decodedJWT));
        } catch (TokenExpiredException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        return true;
    }
}
