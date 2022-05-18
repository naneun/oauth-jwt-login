package com.naneun.mall.auth.service;

import com.naneun.mall.auth.dto.common.LoginResponse;
import com.naneun.mall.auth.dto.common.ResourceServer;
import com.naneun.mall.auth.exception.jwt.JwtRefreshTokenException;
import com.naneun.mall.auth.provider.JwtTokenProvider;
import com.naneun.mall.domain.entity.Member;
import com.naneun.mall.exception.NoSuchMemberException;
import com.naneun.mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtTokenProvider jwtTokenProvider;

    private final MemberRepository memberRepository;

    public LoginResponse login(Member member) {

        String socialId = member.getSocialId();
        ResourceServer resourceServer = member.getResourceServer();
        if (memberRepository.existsBySocialIdAndResourceServer(socialId, resourceServer)) {
            member = memberRepository.findBySocialIdAndResourceServer(socialId, resourceServer)
                    .orElseThrow(NoSuchMemberException::new);
        }
        member = memberRepository.save(member);

        String jwtAccessToken = jwtTokenProvider.issueAccessToken(member);
        String jwtRefreshToken = jwtTokenProvider.issueRefreshToken(member);

        // TODO Redis version..
        member.changeJwtRefreshToken(jwtRefreshToken);
        member = memberRepository.save(member);

        return LoginResponse.of(member.getSocialId(), jwtAccessToken, jwtRefreshToken);
    }

    public Member findUserByIdAndRefreshToken(Long id, String jwtRefreshToken) {
        return memberRepository.findByIdAndJwtRefreshToken(id, jwtRefreshToken)
                .orElseThrow(JwtRefreshTokenException::new);
    }
}
