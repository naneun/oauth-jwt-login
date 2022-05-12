package com.naneun.mall.domain.entity.done;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    public Exhibition(Long id, String title, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.categories = categories;
    }

    public static Exhibition of(String title, List<Category> categories) {
        return Exhibition.builder()
                .title(title)
                .categories(categories)
                .build();
    }

    public static Exhibition of(Long id, String title, List<Category> categories) {
        return Exhibition.builder()
                .id(id)
                .title(title)
                .categories(categories)
                .build();
    }

    public void addCategory(Category category) {
        categories.add(category);
        category.includeInExhibition(this);
    }
}
