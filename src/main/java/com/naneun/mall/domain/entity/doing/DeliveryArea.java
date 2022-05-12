package com.naneun.mall.domain.entity.doing;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryArea {

    private String city;

    @Builder
    public DeliveryArea(String city) {
        this.city = city;
    }
}
