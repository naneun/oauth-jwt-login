package com.naneun.mall.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL)
    private List<Category> categories;

    @Builder
    private Exhibition(Long id, String title) {
        this.id = id;
        this.title = title;
        this.categories = new ArrayList<>();
    }

    public void modifyTitle(String title) {
        this.title = title;
    }

    public void addCategory(Category category) {
        categories.add(category);
        category.changeExhibition(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.changeExhibition(null);
    }
}
