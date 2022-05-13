package com.naneun.mall.domain.embedd;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExcludedArea {

    private String city;

    @Builder
    public ExcludedArea(String city) {
        this.city = city;
    }
}
