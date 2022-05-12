package com.naneun.mall.domain.entity;

import com.naneun.mall.domain.link.ProductToDiscountEvent;
import lombok.*;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "discountEvent", cascade = CascadeType.ALL)
    private List<ProductToDiscountEvent> productToDiscountEvents;

    @Builder
    public DiscountEvent(Long id, String title, double discountRate) {
        this.id = id;
        this.title = title;
        this.discountRate = discountRate;
    }
}
