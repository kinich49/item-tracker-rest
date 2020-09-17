package mx.kinich49.itemtracker.models.front;

import mx.kinich49.itemtracker.models.database.Brand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FrontBrandTest {

    @Test
    @DisplayName("Brand Dto must be null")
    public void shouldReturnNull() {
        //given
        Brand brand = null;
        //then
        assertNull(FrontBrand.from(brand));
        //given
        List<Brand> brands = null;
        //then
        assertNull(FrontBrand.from(brands));
        //given
        brands = Collections.emptyList();
        //then
        assertNull(FrontBrand.from(brands));
    }

    @Test
    @DisplayName("Should return Brand Dto")
    public void shouldReturnBrandDto() {
        //given
        Brand brand = new Brand();
        brand.setName("Test Brand");
        brand.setId(1L);

        //when
        FrontBrand dto = FrontBrand.from(brand);

        //then
        assertNotNull(dto);
        assertEquals(brand.getName(), dto.getName());
        assertEquals(brand.getId(), dto.getId());
    }

    @Test
    @DisplayName("Should return list of Brand Dtos")
    public void shouldReturnListOfDtos() {
        //given
        Brand brandA = new Brand();
        brandA.setName("Test Brand A");
        brandA.setId(1L);

        Brand brandB = new Brand();
        brandB.setName("Test Brand B");
        brandB.setId(2L);

        Brand brandC = new Brand();
        brandC.setName("Test Brand C");
        brandC.setId(3L);

        List<Brand> brands = new ArrayList<>();
        brands.add(brandA);
        brands.add(brandB);
        brands.add(brandC);

        //when
        List<FrontBrand> dtos = FrontBrand.from(brands);

        //then
        assertNotNull(dtos);
        assertFalse(dtos.isEmpty());
        assertEquals(3, dtos.size());
        assertDto(brandA, dtos.get(0));
        assertDto(brandB, dtos.get(1));
        assertDto(brandC, dtos.get(2));
    }

    private void assertDto(Brand expected, FrontBrand actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getId(), actual.getId());
    }
}
