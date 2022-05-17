package com.naneun.mall.domain.link;

import com.naneun.mall.domain.entity.DiscountEvent;
import com.naneun.mall.domain.entity.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    private ProductToDiscountEvent(Long id, Product product, DiscountEvent discountEvent) {
        this.id = id;
        this.product = product;
        this.discountEvent = discountEvent;
    }

    public void changeProduct(Product product) {
        this.product = product;
    }

    public void changeDiscountEvent(DiscountEvent discountEvent) {
        this.discountEvent = discountEvent;
    }
}
