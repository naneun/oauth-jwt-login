package com.naneun.mall.repository;

import com.naneun.mall.domain.entity.done.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
