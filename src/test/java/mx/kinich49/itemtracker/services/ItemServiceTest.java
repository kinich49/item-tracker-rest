package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.ItemAnalyticsDto;
import mx.kinich49.itemtracker.dtos.ItemDto;
import mx.kinich49.itemtracker.models.Brand;
import mx.kinich49.itemtracker.models.Category;
import mx.kinich49.itemtracker.models.Item;
import mx.kinich49.itemtracker.models.Store;
import mx.kinich49.itemtracker.repositories.ItemRepository;
import mx.kinich49.itemtracker.services.impl.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    ItemServiceImpl subject;
    @Mock
    ItemRepository itemRepository;

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
        testBrandA.setId(1);

        testCategoryA = new Category();
        testCategoryA.setName("Test Category A");
        testCategoryA.setId(1);

        testBrandB = new Brand();
        testBrandB.setName("Test Brand B");
        testBrandB.setId(2);

        testCategoryB = new Category();
        testCategoryB.setName("Test Category B");
        testCategoryB.setId(2);

        testBrandC = new Brand();
        testBrandC.setName("Test Brand C");
        testBrandC.setId(3);

        testCategoryC = new Category();
        testCategoryC.setName("Test Category C");
        testCategoryC.setId(3);
    }

    @Test
    @DisplayName("Should Return 3 DTOs")
    void shouldReturnDtos() {
        //Given
        List<Item> items = new ArrayList<>();
        Item itemA = new Item();
        itemA.setName("Item A");
        itemA.setId(1);
        testBrandA.addItem(itemA);
        testCategoryA.addItem(itemA);

        Item itemB = new Item();
        itemB.setName("Item B");
        itemB.setId(2);
        testBrandB.addItem(itemB);
        testCategoryB.addItem(itemB);

        Item itemC = new Item();
        itemC.setName("Item C");
        itemC.setId(2);
        testBrandC.addItem(itemC);
        testCategoryC.addItem(itemC);

        items.add(itemA);
        items.add(itemB);
        items.add(itemC);

        when(itemRepository.findByNameStartsWithIgnoreCase(anyString()))
                .thenReturn(items);

        //when
        Optional<List<ItemDto>> optResult = subject.findLike("Item");

        //then
        assertNotNull(optResult);
        assertTrue(optResult.isPresent());
        List<ItemDto> dtos = optResult.get();
        assertEquals(3, dtos.size());

        ItemDto dtoA = dtos.get(0);
        assertEquals(itemA.getName(), dtoA.getName());
        assertEquals(testBrandA.getName(), dtoA.getBrand().getName());
        assertEquals(testCategoryA.getName(), dtoA.getCategory().getName());

        ItemDto dtoB = dtos.get(1);
        assertEquals(itemB.getName(), dtoB.getName());
        assertEquals(testBrandB.getName(), dtoB.getBrand().getName());
        assertEquals(testCategoryB.getName(), dtoB.getCategory().getName());

        ItemDto dtoC = dtos.get(2);
        assertEquals(itemC.getName(), dtoC.getName());
        assertEquals(testBrandC.getName(), dtoC.getBrand().getName());
        assertEquals(testCategoryC.getName(), dtoC.getCategory().getName());
    }

    @Test
    @DisplayName("Empty result when list is null")
    public void shouldReturnEmptyWhenRepositoryReturnsNullList() {
        //given
        when(itemRepository.findByNameStartsWithIgnoreCase(anyString()))
                .thenReturn(Collections.emptyList());

        //when
        Optional<List<ItemDto>> result = subject.findLike("Item");

        //then
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Empty result when list is empty")
    public void shouldReturnEmptyWhenRepositoryReturnsEmptyList() {
        //given
        when(itemRepository.findByNameStartsWithIgnoreCase(anyString()))
                .thenReturn(null);

        //when
        Optional<List<ItemDto>> result = subject.findLike("Item");

        //then
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Empty result when name is null️")
    public void shouldReturnEmptyWhenNameIsNull() {
        //when
        Optional<List<ItemDto>> result = subject.findLike(null);

        //then
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Empty DTOs when given name is empty")
    public void shouldReturnEmptyWhenNameIsEmpty() {
        //when
        Optional<List<ItemDto>> result = subject.findLike("");

        //then
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Empty Average  when item id does not exist")
    public void shouldReturnEmptyAverageWhenItemDoesNotExist() {
        //given
        when(itemRepository.existsById(anyLong()))
                .thenReturn(false);

        //when
        Optional<ItemAnalyticsDto> result = subject.getAnalyticsFor(1);

        //then
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Returns Valid Analytics DTO")
    public void shouldReturnValidAnalyticsDTO() {
        //given
        Tuple storeAndDateAndPriceMock = mock(Tuple.class);
        Tuple averagePriceAndCurrencyMock = mock(Tuple.class);

        Store testStore = new Store();
        testStore.setName("Test Store");
        testStore.setId(1);

        LocalDate testDate = LocalDate.of(2020, 1, 1);
        Integer testLatestPrice = 1000;
        Double testAveragePrice = 2850.00;
        String testCurrency = "MXN";
        when(storeAndDateAndPriceMock.get(eq(0), eq(Store.class)))
                .thenReturn(testStore);
        when(storeAndDateAndPriceMock.get(eq(1), eq(LocalDate.class)))
                .thenReturn(testDate);
        when(storeAndDateAndPriceMock.get(eq(2), eq(Integer.class)))
                .thenReturn(testLatestPrice);

        List<Tuple> tuples = new ArrayList<>();
        when(averagePriceAndCurrencyMock.get(eq(0), eq(Double.class)))
                .thenReturn(testAveragePrice);
        when(averagePriceAndCurrencyMock.get(eq(1), eq(String.class)))
                .thenReturn(testCurrency);

        tuples.add(averagePriceAndCurrencyMock);

        when(itemRepository.existsById(eq(1L)))
                .thenReturn(true);
        when(itemRepository.findLatestStoreAndShoppingDateAndPrice(anyLong()))
                .thenReturn(Optional.of(storeAndDateAndPriceMock));
        when(itemRepository.findAverageUnitPriceAndCurrency(anyLong()))
                .thenReturn(tuples);

        //when
        Optional<ItemAnalyticsDto> result = subject.getAnalyticsFor(1L);

        //then
        assertTrue(result.isPresent());

        ItemAnalyticsDto dto = result.get();
        assertEquals(testStore.getName(), dto.getLatestStore());
        assertEquals(testDate.toString(), dto.getLatestDate());
        assertEquals("$28.50 MXN", dto.getAveragePrice());
        assertEquals("$10.00 MXN", dto.getLatestPrice());
    }
}
