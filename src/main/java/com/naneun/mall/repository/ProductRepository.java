package com.naneun.mall.repository;

import com.naneun.mall.domain.entity.done.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
