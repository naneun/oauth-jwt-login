package com.naneun.mall.domain.entity;

import com.naneun.mall.domain.link.CategoryToProduct;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"subCategories", "exhibition"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Category parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Exhibition exhibition;

    @OneToMany(mappedBy = "category")
    private List<CategoryToProduct> categoryToProducts;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> subCategories;

    @Builder
    private Category(Long id, String title, Category parent, Exhibition exhibition) {
        this.id = id;
        this.title = title;
        this.parent = parent;
        this.categoryToProducts = new ArrayList<>();
        this.subCategories = new ArrayList<>();
        this.exhibition = exhibition;
    }

    public void changeParent(Category category) {
        this.parent = category;
    }

    public void changeExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
        for (Category subCategory : subCategories) {
            subCategory.changeExhibition(exhibition);
        }
    }

    public void changeSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public void addSubCategory(Category category) {
        subCategories.add(category);
        category.changeParent(this);
    }

    public void removeSubCategory(Category category) {
        subCategories.remove(category);
        category.changeParent(null);
    }
}
