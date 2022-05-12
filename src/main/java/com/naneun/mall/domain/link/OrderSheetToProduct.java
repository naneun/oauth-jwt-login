package com.naneun.mall.domain.link;

import com.naneun.mall.domain.entity.OrderSheet;
import com.naneun.mall.domain.entity.Product;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
