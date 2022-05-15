package com.naneun.mall.service;

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
}