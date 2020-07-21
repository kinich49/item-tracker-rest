package mx.kinich49.itemtracker.models.front;

import mx.kinich49.itemtracker.models.database.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FrontCategoryTest {


    @Test
    @DisplayName("Category Dto must be null")
    public void shouldReturnNull() {
        //given
        Category category = null;
        //then
        assertNull(FrontCategory.from(category));
        //given
        List<Category> categories = null;
        //then
        assertNull(FrontCategory.from(categories));
        //given
        categories = Collections.emptyList();
        //then
        assertNull(FrontCategory.from(categories));
    }

    @Test
    @DisplayName("Should return Category Dto")
    public void shouldReturnCategoryDto() {
        //given
        Category category = new Category();
        category.setName("Test Category");
        category.setId(1);

        //when
        FrontCategory dto = FrontCategory.from(category);

        //then
        assertNotNull(dto);
        assertEquals(category.getName(), dto.getName());
        assertEquals(category.getId(), dto.getId());
    }

    @Test
    @DisplayName("Should return list of Category Dtos")
    public void shouldReturnListOfDtos() {
        //given
        Category categoryA = new Category();
        categoryA.setName("Test Category A");
        categoryA.setId(1);

        Category categoryB = new Category();
        categoryB.setName("Test Category B");
        categoryB.setId(2);

        Category categoryC = new Category();
        categoryC.setName("Test Category C");
        categoryC.setId(3);

        List<Category> categories = new ArrayList<>();
        categories.add(categoryA);
        categories.add(categoryB);
        categories.add(categoryC);

        //when
        List<FrontCategory> dtos = FrontCategory.from(categories);

        //then
        assertNotNull(dtos);
        assertFalse(dtos.isEmpty());
        assertEquals(3, dtos.size());
        assertDto(categoryA, dtos.get(0));
        assertDto(categoryB, dtos.get(1));
        assertDto(categoryC, dtos.get(2));
    }

    private void assertDto(Category expected, FrontCategory actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getId(), actual.getId());
    }
}
