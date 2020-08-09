package mx.kinich49.itemtracker.models.mobile.responses;

import mx.kinich49.itemtracker.models.database.Brand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MobileBrandResponseTest {

    @Test
    @DisplayName("Should Return a brand response with null mobile id")
    public void shouldReturnBrandResponse() {
        //Given
        Brand brand = new Brand();
        brand.setName("Test Brand");
        brand.setId(20L);

        //when
        MobileBrandResponse response = MobileBrandResponse.from(brand, null);

        //then
        assertNotNull(response);
        assertEquals(brand.getName(), response.getName());
        assertEquals(brand.getId(), response.getId());
        assertNull(response.getMobileId());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    @DisplayName("Should return null response")
    public void shouldReturnNullResponse() {
        //when
        MobileBrandResponse response = MobileBrandResponse.from(null, null);

        //then
        assertNull(response);
    }

    @Test
    @DisplayName("Should return a brand response with mobile id")
    public void shouldReturnBrandResponseWithMobileId() {
        //given
        Brand brand = new Brand();
        brand.setId(10L);
        brand.setName("Test Brand");
        Long mobileId = 1L;

        //when
        MobileBrandResponse response = MobileBrandResponse.from(brand, mobileId);

        //then
        assertNotNull(response);
        assertEquals(brand.getId(), response.getId());
        assertEquals(brand.getName(), response.getName());
        assertEquals(mobileId, response.getMobileId());
    }
}
