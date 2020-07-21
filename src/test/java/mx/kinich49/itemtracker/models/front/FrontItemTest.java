package mx.kinich49.itemtracker.models.front;

import mx.kinich49.itemtracker.models.database.Brand;
import mx.kinich49.itemtracker.models.database.Category;
import mx.kinich49.itemtracker.models.database.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FrontItemTest {

    @Test
    @DisplayName("Item Dto must be null")
    public void shouldReturnNull() {
        //given
        Item item = null;
        //then
        assertNull(FrontItem.from(item));
        //given
        List<Item> items = null;
        //then
        assertNull(FrontItem.from(items));
        //given
        items = Collections.emptyList();
        //then
        assertNull(FrontItem.from(items));
    }

    @Test
    @DisplayName("Should Return Item Dto")
    public void shouldReturnItemDto() {
        //given
        Item item = new Item();
        item.setName("Test Item");
        item.setId(1);
        Category category = new Category();
        category.setName("Test Category");
        category.setId(1);
        category.addItem(item);

        Brand brand = new Brand();
        brand.setName("Test Brand");
        brand.setId(1);
        brand.addItem(item);

        //when
        FrontItem dto = FrontItem.from(item);

        //then
        assertFullDto(item, dto);
    }

    @Test
    @DisplayName("Should return list of Item Dtos")
    public void shouldReturnListOfDtos() {

        Category categoryA = new Category();
        categoryA.setName("Test Category A");
        categoryA.setId(1);

        Category categoryB = new Category();
        categoryB.setName("Test Category B");
        categoryB.setId(2);

        Category categoryC = new Category();
        categoryC.setName("Test Category C");
        categoryC.setId(2);

        Brand brandA = new Brand();
        brandA.setName("Test Brand A");
        brandA.setId(1);

        Brand brandB = new Brand();
        brandB.setName("Test Brand B");
        brandB.setId(2);

        Brand brandC = new Brand();
        brandC.setName("Test Brand C");
        brandC.setId(3);

        //given
        Item itemA = new Item();
        itemA.setName("Test Item A");
        itemA.setId(1);
        categoryA.addItem(itemA);
        brandA.addItem(itemA);

        Item itemB = new Item();
        itemB.setName("Test Item B");
        itemB.setId(2);
        categoryB.addItem(itemB);
        brandB.addItem(itemB);

        Item itemC = new Item();
        itemC.setName("Test Item C");
        itemC.setId(3);
        categoryC.addItem(itemC);
        brandC.addItem(itemC);

        List<Item> items = new ArrayList<>();
        items.add(itemA);
        items.add(itemB);
        items.add(itemC);

        //when
        List<FrontItem> dtos = FrontItem.from(items);

        //then
        assertNotNull(dtos);
        assertFalse(dtos.isEmpty());
        assertEquals(3, dtos.size());
        assertFullDto(itemA, dtos.get(0));
        assertFullDto(itemB, dtos.get(1));
        assertFullDto(itemC, dtos.get(2));
    }

    private void assertFullDto(Item expected, FrontItem actual) {
        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getId(), actual.getId());
        assertBrandDto(expected.getBrand(), actual.getBrand());
        assertCategoryDto(expected.getCategory(), actual.getCategory());
    }

    private void assertBrandDto(Brand expected, FrontBrand actual) {
        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getId(), actual.getId());
    }

    private void assertCategoryDto(Category expected, FrontCategory actual) {
        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getId(), actual.getId());
    }
}
