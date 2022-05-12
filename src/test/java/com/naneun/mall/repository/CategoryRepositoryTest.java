package com.naneun.mall.repository;

import com.naneun.mall.domain.entity.done.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

    final CategoryRepository categoryRepository;

    @Autowired
    CategoryRepositoryTest(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Test
    void 입력_테스트() {
        Category mainCategory3 = Category.builder()
                .title("상위_카테고리3")
                .subCategories(new ArrayList<>())
                .build();

        Category subCategory3_1 = Category.builder()
                .title("하위_카테고리3_1")
                .build();

        Category subCategory3_2 = Category.builder()
                .title("하위_카테고리3_2")
                .build();

        Category mainCategory4 = Category.builder()
                .title("상위_카테고리4")
                .subCategories(new ArrayList<>())
                .build();

        Category subCategory4_1 = Category.builder()
                .title("하위_카테고리4_1")
                .build();

        Category subCategory4_2 = Category.builder()
                .title("하위_카테고리4_2")
                .build();

        mainCategory3.addSubCategory(subCategory3_1);
        mainCategory3.addSubCategory(subCategory3_2);

        categoryRepository.save(mainCategory3);
        Optional<Category> mainCategory1Result = categoryRepository.findById(mainCategory3.getId());

        mainCategory4.addSubCategory(subCategory4_1);
        mainCategory4.addSubCategory(subCategory4_2);

        categoryRepository.save(mainCategory4);
        Optional<Category> mainCategory2Result = categoryRepository.findById(mainCategory4.getId());

        assertThat(mainCategory1Result).isNotEmpty();
        assertThat(mainCategory2Result).isNotEmpty();

        mainCategory3 = mainCategory1Result.get();
        mainCategory4 = mainCategory2Result.get();

        assertThat(mainCategory3.getId()).isEqualTo(7L);
        assertThat(mainCategory3.getTitle()).isEqualTo("상위_카테고리3");
        assertThat(mainCategory3.getParent()).isNull();

        List<Category> subCategoriesInMainCategory1 = mainCategory3.getSubCategories();

        assertThat(subCategoriesInMainCategory1.size()).isEqualTo(2);

        subCategory3_1 = subCategoriesInMainCategory1.get(0);
        assertThat(subCategory3_1.getId()).isEqualTo(8L);
        assertThat(subCategory3_1.getTitle()).isEqualTo("하위_카테고리3_1");
        assertThat(subCategory3_1.getParent()).isEqualTo(mainCategory3);

        subCategory3_2 = subCategoriesInMainCategory1.get(1);
        assertThat(subCategory3_2.getId()).isEqualTo(9L);
        assertThat(subCategory3_2.getTitle()).isEqualTo("하위_카테고리3_2");
        assertThat(subCategory3_2.getParent()).isEqualTo(mainCategory3);

        assertThat(mainCategory4.getId()).isEqualTo(10L);
        assertThat(mainCategory4.getTitle()).isEqualTo("상위_카테고리4");
        assertThat(mainCategory4.getParent()).isNull();

        List<Category> subCategoriesInMainCategory2 = mainCategory4.getSubCategories();

        assertThat(subCategoriesInMainCategory2.size()).isEqualTo(2);

        subCategory4_1 = subCategoriesInMainCategory2.get(0);
        assertThat(subCategory4_1.getId()).isEqualTo(11L);
        assertThat(subCategory4_1.getTitle()).isEqualTo("하위_카테고리4_1");
        assertThat(subCategory4_1.getParent()).isEqualTo(mainCategory4);

        subCategory4_2 = subCategoriesInMainCategory2.get(1);
        assertThat(subCategory4_2.getId()).isEqualTo(12L);
        assertThat(subCategory4_2.getTitle()).isEqualTo("하위_카테고리4_2");
        assertThat(subCategory4_2.getParent()).isEqualTo(mainCategory4);
    }
}
