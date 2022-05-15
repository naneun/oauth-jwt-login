package com.naneun.mall.auth.service;

import com.naneun.mall.domain.entity.Member;
import com.naneun.mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member findUserByIdAndRefreshToken(Long id, String jwtRefreshToken) {
        return memberRepository.findByIdAndJwtRefreshToken(id, jwtRefreshToken)
                .orElseThrow(RuntimeException::new);
    }
}
