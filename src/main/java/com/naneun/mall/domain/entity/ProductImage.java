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

    private int imageSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;

    @Builder
    private ProductImage(Long id, String imageUrl, int imageSeq, Product product) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.imageSeq = imageSeq;
        this.product = product;
    }

    public void changeImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void changeImageSeq(int imageSeq) {
        this.imageSeq = imageSeq;
    }

    public void changeProduct(Product product) {
        this.product = product;
    }
}
