package com.naneun.mall.domain.entity;

import com.naneun.mall.domain.link.ProductToDiscountEvent;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"productToDiscountEvents"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiscountEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private double discountRate;

    @OneToMany(mappedBy = "discountEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductToDiscountEvent> productToDiscountEvents;

    @Builder
    private DiscountEvent(Long id, String title, double discountRate) {
        this.id = id;
        this.title = title;
        this.discountRate = discountRate;
        this.productToDiscountEvents = new ArrayList<>();
    }

    public void modifyTitle(String title) {
        this.title = title;
    }

    public void changeDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public void addProductToDiscountEvent(ProductToDiscountEvent productToDiscountEvent) {
        productToDiscountEvents.add(productToDiscountEvent);
        productToDiscountEvent.changeDiscountEvent(this);
    }

    public void removeProductToDiscountEvent(ProductToDiscountEvent productToDiscountEvent) {
        productToDiscountEvents.remove(productToDiscountEvent);
        productToDiscountEvent.changeDiscountEvent(null);
    }
}
