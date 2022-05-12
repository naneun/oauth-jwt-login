package com.naneun.mall.domain.link;

import com.naneun.mall.domain.entity.done.DiscountEvent;
import com.naneun.mall.domain.entity.done.Product;

import javax.persistence.*;

@Entity
public class ProductToDiscountEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private DiscountEvent discountEvent;
}
