package com.naneun.mall.domain.link;

import com.naneun.mall.domain.entity.OrderSheet;
import com.naneun.mall.domain.entity.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    private OrderSheetToProduct(Long id, OrderSheet orderSheet, Product product, int price, int count) {
        this.id = id;
        this.orderSheet = orderSheet;
        this.product = product;
        this.price = price;
        this.count = count;
    }
}
