package com.naneun.mall.auth.controller;

import com.naneun.mall.auth.annotation.LoginId;
import com.naneun.mall.auth.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/logout")
public class LogoutController {

    private final RedisService redisService;

    @GetMapping
    public ResponseEntity<Void> login(@LoginId Long loginId) {
        redisService.removeJwtRefreshToken(loginId.toString());
        log.debug("loginId: {}", redisService.getJwtRefreshToken(loginId.toString()));
        return ResponseEntity.ok().build();
    }
}
