package com.naneun.mall.auth;

import com.naneun.mall.domain.entity.Member;
import com.naneun.mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member findUserByIdAndRefreshToken(Long id, String refreshToken) {
        Member member = memberRepository.findById(id)
                .orElseThrow();

        validateRefreshToken(member, refreshToken);
        return member;
    }

    private void validateRefreshToken(Member member, String refreshToken) {
        if (!member.isSameRefreshToken(refreshToken)) {
            throw new RuntimeException("");
        }
    }
}
