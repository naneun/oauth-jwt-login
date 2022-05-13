package com.naneun.mall.domain.entity;

import com.naneun.mall.domain.enumeration.DeliveryType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    private int fee;

    private int freeCondition;

    @Builder
    private Delivery(Long id, DeliveryType deliveryType, int fee, int freeCondition) {
        this.id = id;
        this.deliveryType = deliveryType;
        this.fee = fee;
        this.freeCondition = freeCondition;
    }
}
