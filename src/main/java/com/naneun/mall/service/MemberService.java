package com.naneun.mall.service;

import com.naneun.mall.auth.dto.common.ResourceServer;
import com.naneun.mall.domain.entity.Member;
import com.naneun.mall.exception.NoSuchMemberException;
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
                .orElseThrow(NoSuchMemberException::new);
    }

    public boolean existsMember(String userId, ResourceServer resourceServer) {
        return memberRepository.existsBySocialIdAndResourceServer(userId, resourceServer);
    }
}
