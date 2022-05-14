package com.naneun.mall.repository;

import com.naneun.mall.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>  {

}
