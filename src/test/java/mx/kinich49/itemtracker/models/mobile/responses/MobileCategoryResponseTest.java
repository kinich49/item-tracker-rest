package mx.kinich49.itemtracker.models.mobile.responses;

import mx.kinich49.itemtracker.models.database.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MobileCategoryResponseTest {

    @Test
    @DisplayName("Should return a category response with null mobile id")
    public void shouldReturnCategoryResponse() {
        //Given
        Category category = new Category();
        category.setName("Test Category");
        category.setId(20L);

        //when
        MobileCategoryResponse response = MobileCategoryResponse.from(category, null);

        //then
        assertNotNull(response);
        assertEquals(category.getName(), response.getName());
        assertEquals(category.getId(), response.getId());
        assertNull(response.getMobileId());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    @DisplayName("Should return null response")
    public void shouldReturnNullResponse() {
        //when
        MobileCategoryResponse response = MobileCategoryResponse.from(null, null);

        //then
        assertNull(response);
    }

    @Test
    @DisplayName("Should return a category response with mobile id")
    public void shouldReturnCategoryResponseWithMobileId() {
        //given
        Category category = new Category();
        category.setId(10L);
        category.setName("Test Category");
        Long mobileId = 1L;

        //when
        MobileCategoryResponse response = MobileCategoryResponse.from(category, mobileId);

        //then
        assertNotNull(response);
        assertEquals(category.getId(), response.getId());
        assertEquals(category.getName(), response.getName());
        assertEquals(mobileId, response.getMobileId());
    }
}
