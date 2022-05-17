package com.naneun.mall.repository;

import com.naneun.mall.auth.dto.common.ResourceServer;
import com.naneun.mall.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>  {

    Optional<Member> findBySocialIdAndResourceServer(String userId, ResourceServer resourceServer);

    Optional<Member> findByIdAndJwtRefreshToken(Long id, String jwtRefreshToken);

    boolean existsBySocialIdAndResourceServer(String userId, ResourceServer resourceServer);

    @Query("select m.oauthRefreshToken from Member m where m.id = :id")
    String findOAuthRefreshTokenById(Long id);
}
