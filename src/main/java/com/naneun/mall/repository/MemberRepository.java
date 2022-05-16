package com.naneun.mall.repository;

import com.naneun.mall.auth.dto.common.ResourceServer;
import com.naneun.mall.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>  {

    Optional<Member> findByIdAndJwtRefreshToken(Long id, String jwtRefreshToken);

    Optional<Member> findBySocialIdAndResourceServer(String userId, ResourceServer resourceServer);

    boolean existsBySocialIdAndResourceServer(String userId, ResourceServer resourceServer);
}
