package com.naneun.mall.repository;

import com.naneun.mall.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>  {

    Optional<Member> findByIdAndJwtRefreshToken(Long id, String jwtRefreshToken);
}
