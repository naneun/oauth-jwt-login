package com.naneun.mall.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    final ProductRepository productRepository;

    @Autowired
    ProductRepositoryTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Test
    void test() {
        System.out.println(productRepository.findAll());
    }
}