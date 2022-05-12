package com.naneun.mall.domain.entity.done;

import com.naneun.mall.domain.link.CategoryToProduct;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> subCategories;

    @Builder
    public Category(Long id, String title, Category parent, List<CategoryToProduct> categoryToProducts,
                    List<Category> subCategories, Exhibition exhibition) {

        this.id = id;
        this.title = title;
        this.parent = parent;
        this.categoryToProducts = categoryToProducts;
        this.subCategories = subCategories;
        this.exhibition = exhibition;
    }

    public void setParent(Category category) {
        this.parent = category;
    }

    public void addSubCategory(Category category) {
        subCategories.add(category);
        category.setParent(this);
    }

    public void removeSubCategory(Category category) {
        subCategories.remove(category);
    }

    public void includeInExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
        for (Category subCategory : subCategories) {
            subCategory.includeInExhibition(exhibition);
        }
    }
}
