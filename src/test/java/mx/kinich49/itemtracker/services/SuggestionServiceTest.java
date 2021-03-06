package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.models.front.FrontBrand;
import mx.kinich49.itemtracker.models.front.FrontCategory;
import mx.kinich49.itemtracker.models.front.FrontItem;
import mx.kinich49.itemtracker.dtos.SuggestionsDto;
import mx.kinich49.itemtracker.models.database.Brand;
import mx.kinich49.itemtracker.models.database.Category;
import mx.kinich49.itemtracker.models.database.Item;
import mx.kinich49.itemtracker.repositories.BrandRepository;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import mx.kinich49.itemtracker.repositories.ItemRepository;
import mx.kinich49.itemtracker.services.impl.SuggestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuggestionServiceTest {

    @InjectMocks
    private SuggestionServiceImpl subject;
    @Mock
    ItemRepository itemRepository;
    @Mock
    BrandRepository brandRepository;
    @Mock
    CategoryRepository categoryRepository;

    Brand testBrandA;
    Category testCategoryA;
    Brand testBrandB;
    Category testCategoryB;
    Brand testBrandC;
    Category testCategoryC;

    @BeforeEach
    void setUp() {
        testBrandA = new Brand();
        testBrandA.setName("Test Brand A");
        testBrandA.setId(1L);

        testCategoryA = new Category();
        testCategoryA.setName("Test Category A");
        testCategoryA.setId(1L);

        testBrandB = new Brand();
        testBrandB.setName("Test Brand B");
        testBrandB.setId(2L);

        testCategoryB = new Category();
        testCategoryB.setName("Test Category B");
        testCategoryB.setId(2L);

        testBrandC = new Brand();
        testBrandC.setName("Test Brand C");
        testBrandC.setId(3L);

        testCategoryC = new Category();
        testCategoryC.setName("Test Category C");
        testCategoryC.setId(3L);
    }

    @Test
    @DisplayName("Find Item Dtos like name")
    public void shouldReturnItemDtos() {
        //Given
        List<Item> items = new ArrayList<>();
        Item itemA = new Item();
        itemA.setName("Item A");
        itemA.setId(1L);
        testBrandA.addItem(itemA);
        testCategoryA.addItem(itemA);

        Item itemB = new Item();
        itemB.setName("Item B");
        itemB.setId(2L);
        testBrandB.addItem(itemB);
        testCategoryB.addItem(itemB);

        Item itemC = new Item();
        itemC.setName("Item C");
        itemC.setId(2L);
        testBrandC.addItem(itemC);
        testCategoryC.addItem(itemC);

        items.add(itemA);
        items.add(itemB);
        items.add(itemC);

        when(itemRepository.findByNameStartsWithIgnoreCase(anyString()))
                .thenReturn(items);

        //when
        List<FrontItem> dtos = subject.findItemsLike("Test");

        //then
        assertNotNull(dtos);
        assertEquals(3, dtos.size());

        FrontItem dtoA = dtos.get(0);
        assertEquals(itemA.getName(), dtoA.getName());
        assertEquals(testBrandA.getName(), dtoA.getBrand().getName());
        assertEquals(testCategoryA.getName(), dtoA.getCategory().getName());

        FrontItem dtoB = dtos.get(1);
        assertEquals(itemB.getName(), dtoB.getName());
        assertEquals(testBrandB.getName(), dtoB.getBrand().getName());
        assertEquals(testCategoryB.getName(), dtoB.getCategory().getName());

        FrontItem dtoC = dtos.get(2);
        assertEquals(itemC.getName(), dtoC.getName());
        assertEquals(testBrandC.getName(), dtoC.getBrand().getName());
        assertEquals(testCategoryC.getName(), dtoC.getCategory().getName());
    }

    @Test
    @DisplayName("Find Brands Dtos like name")
    public void shouldReturnBrandDtos() {
        //given
        Brand brandA = new Brand();
        brandA.setName("Brand A");
        brandA.setId(1L);
        Brand brandB = new Brand();
        brandB.setName("Brand B");
        brandB.setId(2L);
        Brand brandC = new Brand();
        brandC.setName("Brand C");
        brandC.setId(3L);

        List<Brand> brands = new ArrayList<>();
        brands.add(brandA);
        brands.add(brandB);
        brands.add(brandC);

        when(brandRepository.findByNameStartsWithIgnoreCase(anyString()))
                .thenReturn(brands);

        //when
        List<FrontBrand> dtos = subject.findBrandsLike("Test");
        assertNotNull(dtos);
        assertEquals(3, dtos.size());

        FrontBrand dtoA = dtos.get(0);
        assertEquals(brandA.getName(), dtoA.getName());

        FrontBrand dtoB = dtos.get(1);
        assertEquals(brandB.getName(), dtoB.getName());

        FrontBrand dtoC = dtos.get(2);
        assertEquals(brandC.getName(), dtoC.getName());
    }

    @Test
    @DisplayName("Find Category Dtos like name")
    public void shouldReturnCategoryDtos() {
        //given
        Category categoryA = new Category();
        categoryA.setName("Category A");
        categoryA.setId(1L);
        Category categoryB = new Category();
        categoryB.setName("Category B");
        categoryB.setId(2L);
        Category categoryC = new Category();
        categoryC.setName("Category C");
        categoryC.setId(3L);

        List<Category> categories = new ArrayList<>();
        categories.add(categoryA);
        categories.add(categoryB);
        categories.add(categoryC);

        when(categoryRepository.findByNameStartsWithIgnoreCase(anyString()))
                .thenReturn(categories);

        //when
        List<FrontCategory> dtos = subject.findCategoriesLike("Test");

        assertNotNull(dtos);
        assertEquals(3, dtos.size());

        FrontCategory dtoA = dtos.get(0);
        assertEquals(categoryA.getName(), dtoA.getName());

        FrontCategory dtoB = dtos.get(1);
        assertEquals(categoryB.getName(), dtoB.getName());

        FrontCategory dtoC = dtos.get(2);
        assertEquals(categoryC.getName(), dtoC.getName());
    }

    @Test
    @DisplayName("Empty Optional when name is null or empty")
    public void shouldReturnEmptyWhenNameIsNullOrEmpty() {
        //when
        Optional<SuggestionsDto> optDto = subject.findSuggestionsLike(null);
        //then
        assertFalse(optDto.isPresent());

        //when
        optDto = subject.findSuggestionsLike("");
        //then
        assertFalse(optDto.isPresent());
    }

    @Test
    @DisplayName("Empty Optional when name is less than 3 characters")
    public void shouldReturnEmptyWhenNameIsTooShort() {
        Optional<SuggestionsDto> optDto;

        //when
        optDto = subject.findSuggestionsLike("a");
        //then
        assertFalse(optDto.isPresent());

        //when
        optDto = subject.findSuggestionsLike("ab");
        //then
        assertFalse(optDto.isPresent());

        //when
        optDto = subject.findSuggestionsLike("abc");
        //then
        assertFalse(optDto.isPresent());
    }

    @Test
    @DisplayName("Should return SuggestionsDto")
    public void shouldReturnSuggestionDto() {
        //given
        List<Item> items = new ArrayList<>();
        Item itemA = new Item();
        itemA.setName("Item A");
        itemA.setId(1L);
        testBrandA.addItem(itemA);
        testCategoryA.addItem(itemA);

        Item itemB = new Item();
        itemB.setName("Item B");
        itemB.setId(2L);
        testBrandB.addItem(itemB);
        testCategoryB.addItem(itemB);

        Item itemC = new Item();
        itemC.setName("Item C");
        itemC.setId(2L);
        testBrandC.addItem(itemC);
        testCategoryC.addItem(itemC);

        items.add(itemA);
        items.add(itemB);
        items.add(itemC);

        List<Category> categories = new ArrayList<>();
        categories.add(testCategoryA);
        categories.add(testCategoryB);
        categories.add(testCategoryC);

        List<Brand> brands = new ArrayList<>();
        brands.add(testBrandA);
        brands.add(testBrandB);
        brands.add(testBrandC);

        when(itemRepository.findByNameStartsWithIgnoreCase(anyString()))
                .thenReturn(items);

        when(brandRepository.findByNameStartsWithIgnoreCase(anyString()))
                .thenReturn(brands);

        when(categoryRepository.findByNameStartsWithIgnoreCase(anyString()))
                .thenReturn(categories);

        //when
        Optional<SuggestionsDto> optDto = subject.findSuggestionsLike("Test");

        //then
        assertTrue(optDto.isPresent());
        SuggestionsDto dto = optDto.get();
        assertNotNull(dto.getItems());
        assertEquals(3, dto.getItems().size());
        assertNotNull(dto.getBrands());
        assertEquals(3, dto.getBrands().size());
        assertNotNull(dto.getCategories());
        assertEquals(3, dto.getCategories().size());
    }

}
