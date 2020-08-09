package mx.kinich49.itemtracker.models.mobile.responses;

import mx.kinich49.itemtracker.models.database.Brand;
import mx.kinich49.itemtracker.models.database.Category;
import mx.kinich49.itemtracker.models.database.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class MobileItemResponseTest {

    @Test
    @DisplayName("Should return an item response with null mobile id and non-null category and brand")
    public void shouldReturnItemResponseWithBrandAndCategory() {
        //Given
        Item item = new Item();
        item.setName("Test Item");
        item.setId(20L);

        Brand brand = new Brand();
        brand.setId(21L);
        brand.setName("Test Brand");
        brand.addItem(item);

        Category category = new Category();
        category.setId(22L);
        category.setName("Test Category");
        category.addItem(item);

        //when
        MobileItemResponse response = MobileItemResponse.from(item, null, null, null);

        //then
        assertNotNull(response);
        assertEquals(item.getName(), response.getName());
        assertEquals(item.getId(), response.getId());
        assertNull(response.getMobileId());

        verifyBrand(brand, null, response.getBrand());
        verifyCategory(category, null, response.getCategory());
    }

    @Test
    @DisplayName("Should return an item response with null mobile id, null brand and non-null category")
    public void shouldReturnItemResponseWithCategory() {
        //Given
        Item item = new Item();
        item.setName("Test Item");
        item.setId(20L);

        Category category = new Category();
        category.setId(22L);
        category.setName("Test Category");
        category.addItem(item);

        //when
        MobileItemResponse response = MobileItemResponse.from(item, null,
                null, null);

        //then
        assertNotNull(response);
        assertEquals(item.getName(), response.getName());
        assertEquals(item.getId(), response.getId());
        assertNull(response.getMobileId());

        verifyBrand(null, null, response.getBrand());
        verifyCategory(category, null, response.getCategory());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    @DisplayName("Should return null response")
    public void shouldReturnNullResponse() {
        //when
        MobileItemResponse response = MobileItemResponse.from(null, null,
                null, null);

        //then
        assertNull(response);
    }

    @Test
    @DisplayName("Should return an item response with  non-null mobile id, category and brand")
    public void shouldReturnItemResponseWithBrandAndCategoryAndMobileId() {
        //Given
        Item item = new Item();
        item.setName("Test Item");
        item.setId(20L);
        Long mobileId = 10L;

        Brand brand = new Brand();
        brand.setId(21L);
        brand.setName("Test Brand");
        brand.addItem(item);

        Category category = new Category();
        category.setId(22L);
        category.setName("Test Category");
        category.addItem(item);

        //when
        MobileItemResponse response = MobileItemResponse.from(item, mobileId, null, null);

        //then
        assertNotNull(response);
        assertEquals(item.getName(), response.getName());
        assertEquals(item.getId(), response.getId());
        assertEquals(mobileId, response.getMobileId());

        verifyBrand(brand, null, response.getBrand());
        verifyCategory(category, null, response.getCategory());
    }

    @Test
    @DisplayName("Should return an item response with  null brand and non-null mobile id and category")
    public void shouldReturnItemResponseWithCategoryAndMobileId() {
        //Given
        Item item = new Item();
        item.setName("Test Item");
        item.setId(20L);
        Long mobileId = 10L;

        Category category = new Category();
        category.setId(22L);
        category.setName("Test Category");
        category.addItem(item);

        //when
        MobileItemResponse response = MobileItemResponse.from(item, mobileId, null, null);

        //then
        assertNotNull(response);
        assertEquals(item.getName(), response.getName());
        assertEquals(item.getId(), response.getId());
        assertEquals(mobileId, response.getMobileId());

        verifyBrand(null, null, response.getBrand());
        verifyCategory(category, null, response.getCategory());
    }

    private void verifyBrand(Brand expected, Long mobileId, MobileBrandResponse actual) {
        if (expected == null) {
            assertNull(actual);
        } else {
            assertNotNull(expected);
            assertEquals(expected.getId(), actual.getId());
            assertEquals(expected.getName(), actual.getName());
            assertEquals(mobileId, actual.getMobileId());
        }
    }

    private void verifyCategory(Category expected, Long mobileId, MobileCategoryResponse actual) {
        Objects.requireNonNull(expected, "An item must have a category");

        assertNotNull(expected);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(mobileId, actual.getMobileId());
    }
}
