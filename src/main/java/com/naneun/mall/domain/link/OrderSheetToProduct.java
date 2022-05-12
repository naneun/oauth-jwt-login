package com.naneun.mall.domain.link;

import com.naneun.mall.domain.entity.doing.OrderSheet;
import com.naneun.mall.domain.entity.done.Product;

import javax.persistence.*;

@Entity
public class OrderSheetToProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private OrderSheet orderSheet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;

    private int price;

    private int count;
}
