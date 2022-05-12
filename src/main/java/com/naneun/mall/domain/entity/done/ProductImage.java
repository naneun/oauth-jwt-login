package com.naneun.mall.domain.entity.done;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private String imageSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;
}
