package com.naneun.mall.domain.entity;

import com.naneun.mall.domain.embedd.ExcludedArea;
import com.naneun.mall.domain.link.CategoryToProduct;
import com.naneun.mall.domain.link.ProductToDiscountEvent;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    private Product(Long id, String name, String description, int price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.productImages = new ArrayList<>();
        this.categoryToProducts = new ArrayList<>();
        this.productToDiscountEvents = new ArrayList<>();
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryToProduct> categoryToProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductToDiscountEvent> productToDiscountEvents;

    @ElementCollection
    @CollectionTable(name = "excluded_area", joinColumns = @JoinColumn(name = "product_id"))
    private List<ExcludedArea> excludedAreas;

    /* Stock */

    public void addStock(int quantity) {
        stock += quantity;
    }

    public void removeStock(int quantity) {
        // TODO
        stock -= quantity;
    }

    /* CategoryToProduct */

    public void addCategoryToProduct(CategoryToProduct categoryToProduct) {
        categoryToProducts.add(categoryToProduct);
    }

    public void removeCategoryToProduct(CategoryToProduct categoryToProduct) {
        categoryToProducts.remove(categoryToProduct);
    }

    /* ProductImage */

    public void addProductImage(ProductImage productImage) {
        productImages.add(productImage);
        productImage.changeProduct(this);
    }

    public void removeProductImage(ProductImage productImage) {
        productImages.remove(productImage);
        productImage.changeProduct(null);
    }

    /* ProductToDiscountEvent */

    public void addProductToDiscountEvent(ProductToDiscountEvent productToDiscountEvent) {
        productToDiscountEvents.add(productToDiscountEvent);
    }

    public void removeProductToDiscountEvent(ProductToDiscountEvent productToDiscountEvent) {
        productToDiscountEvents.remove(productToDiscountEvent);
    }
}
