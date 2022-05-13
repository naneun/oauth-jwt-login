package com.naneun.mall.domain.link;

import com.naneun.mall.domain.entity.Category;
import com.naneun.mall.domain.entity.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryToProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;

    @Builder
    private CategoryToProduct(Long id, Category category, Product product) {
        this.id = id;
        this.category = category;
        this.product = product;
    }

    public void changeCategory(Category category) {
        this.category = category;
    }

    public void changeProduct(Product product) {
        this.product = product;
    }
}
