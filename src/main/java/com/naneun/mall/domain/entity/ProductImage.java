package com.naneun.mall.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"product"})
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
