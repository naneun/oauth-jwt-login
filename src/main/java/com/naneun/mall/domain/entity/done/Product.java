package com.naneun.mall.domain.entity.done;

import com.naneun.mall.domain.link.CategoryToProduct;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"categoryToProducts"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int price;

    private int stock;

    @OneToMany(mappedBy = "product")
    private List<CategoryToProduct> categoryToProducts;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    public Product(Long id, String name, String description, int price, int stock,
                   List<CategoryToProduct> categoryToProducts,
                   LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryToProducts = categoryToProducts;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
