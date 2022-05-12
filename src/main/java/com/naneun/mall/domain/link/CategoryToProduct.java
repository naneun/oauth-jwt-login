package com.naneun.mall.domain.link;

import com.naneun.mall.domain.entity.done.Category;
import com.naneun.mall.domain.entity.done.Product;

import javax.persistence.*;

@Entity
public class CategoryToProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;
}
