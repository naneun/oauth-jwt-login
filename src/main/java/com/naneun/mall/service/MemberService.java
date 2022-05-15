package com.naneun.mall.service;

import com.naneun.mall.auth.dto.ResourceServer;
import com.naneun.mall.domain.entity.Member;
import com.naneun.mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    public Member findMember(String userId, ResourceServer resourceServer) {
        return memberRepository.findBySocialIdAndResourceServer(userId, resourceServer)
                .orElseThrow();
    }

    public boolean existsMember(String userId, ResourceServer resourceServer) {
        return memberRepository.existsBySocialIdAndResourceServer(userId, resourceServer);
    }
}