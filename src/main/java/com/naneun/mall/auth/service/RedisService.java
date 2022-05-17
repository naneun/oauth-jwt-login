package com.naneun.mall.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveJwtRefreshToken(String userId, String refreshToken) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(userId, refreshToken, Duration.ofDays(14));
    }

    public String getJwtRefreshToken(String userId) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(userId);
    }

    public void removeJwtRefreshToken(Long userId) {
        redisTemplate.delete(String.valueOf(userId));
    }
}
