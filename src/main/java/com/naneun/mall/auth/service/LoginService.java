package com.naneun.mall.auth.service;

import com.naneun.mall.auth.dto.common.ResourceServer;
import com.naneun.mall.domain.entity.Member;
import com.naneun.mall.exception.NoSuchMemberException;
import com.naneun.mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(Member member) {
        String socialId = member.getSocialId();
        ResourceServer resourceServer = member.getResourceServer();
        if (memberRepository.existsBySocialIdAndResourceServer(socialId, resourceServer)) {
            member = memberRepository.findBySocialIdAndResourceServer(socialId, resourceServer)
                    .orElseThrow(NoSuchMemberException::new);
        }
        return memberRepository.save(member);
    }
}
