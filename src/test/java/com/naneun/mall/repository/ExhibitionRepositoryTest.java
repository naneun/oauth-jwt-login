package com.naneun.mall.repository;

import com.naneun.mall.domain.entity.Category;
import com.naneun.mall.domain.entity.Exhibition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ExhibitionRepositoryTest {

    final ExhibitionRepository exhibitionRepository;

    @Autowired
    ExhibitionRepositoryTest(ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    @Test
    void 입력_테스트() {
        Category mainCategory3 = Category.builder()
                .title("상위_카테고리3")
                .build();

        Category subCategory3_1 = Category.builder()
                .title("하위_카테고리3_1")
                .build();

        Category subCategory3_2 = Category.builder()
                .title("하위_카테고리3_2")
                .build();

        Category mainCategory4 = Category.builder()
                .title("상위_카테고리4")
                .build();

        Category subCategory4_1 = Category.builder()
                .title("하위_카테고리4_1")
                .build();

        Category subCategory4_2 = Category.builder()
                .title("하위_카테고리4_2")
                .build();

        mainCategory3.addSubCategory(subCategory3_1);
        mainCategory3.addSubCategory(subCategory3_2);

        mainCategory4.addSubCategory(subCategory4_1);
        mainCategory4.addSubCategory(subCategory4_2);

        Exhibition exhibition = Exhibition.builder()
                .title("기획전3")
                .build();

        exhibition.addCategory(mainCategory3);
        exhibition.addCategory(mainCategory4);

        exhibitionRepository.save(exhibition);

        Optional<Exhibition> ExhibitionResult = exhibitionRepository.findById(exhibition.getId());

        assertThat(ExhibitionResult).isNotEmpty();

        exhibition = ExhibitionResult.get();

        assertThat(exhibition.getId()).isEqualTo(4L);
        assertThat(exhibition.getTitle()).isEqualTo("기획전3");
        assertThat(exhibition.getCategories()).isNotNull();

        List<Category> categories = exhibition.getCategories();
        assertThat(categories.size()).isEqualTo(2);
        assertThat(categories.get(0)).isEqualTo(mainCategory3);
        assertThat(categories.get(1)).isEqualTo(mainCategory4);
    }
}
