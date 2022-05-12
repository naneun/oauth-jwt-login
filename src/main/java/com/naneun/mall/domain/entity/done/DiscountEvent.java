package com.naneun.mall.domain.entity.done;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiscountEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private double discountRate;

    @Builder
    public DiscountEvent(Long id, String title, double discountRate) {
        this.id = id;
        this.title = title;
        this.discountRate = discountRate;
    }
}
