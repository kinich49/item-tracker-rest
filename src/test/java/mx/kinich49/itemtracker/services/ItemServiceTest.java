package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.ItemAnalyticsDto;
import mx.kinich49.itemtracker.models.database.Brand;
import mx.kinich49.itemtracker.models.database.Category;
import mx.kinich49.itemtracker.models.database.Item;
import mx.kinich49.itemtracker.models.database.Store;
import mx.kinich49.itemtracker.models.front.FrontBrand;
import mx.kinich49.itemtracker.models.front.FrontCategory;
import mx.kinich49.itemtracker.models.front.FrontItem;
import mx.kinich49.itemtracker.models.front.FrontShoppingItem;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    ItemServiceImpl subject;
    @Mock
    ItemRepository itemRepository;
    @Mock
    SuggestionService suggestionService;

    private Item testItem;
    private Brand testBrand;
    private Category testCategory;
    private Store testStore;
    private String testCurrency = "MXN";

    @BeforeEach
    void setUp() {
        testItem = new Item();
        testItem.setName("Test Item");
        testItem.setId(1L);

        testBrand = new Brand();
        testBrand.setName("Test Brand");
        testBrand.setId(2L);
        testBrand.addItem(testItem);

        testCategory = new Category();
        testCategory.setName("Test Category");
        testCategory.setId(3L);
        testCategory.addItem(testItem);

        testStore = new Store();
        testStore.setName("Test Store");
        testStore.setId(1);
    }

    @Test
    @DisplayName("Optional must be empty when item id does not exist")
    public void shouldReturn_emptyAverageDto_whenItemIdDoesNotExists() {
        //given
        when(itemRepository.existsById(anyLong()))
                .thenReturn(false);

        //when
        Optional<ItemAnalyticsDto> result = subject.getAnalyticsFor(1, 1L);

        //then
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Get Analytics DTO for valid item id")
    public void shouldReturn_AnalyticsDTO_whenItemIdExists() {
        //given
        Tuple storeAndDateAndPriceMock = mock(Tuple.class);
        Tuple averagePriceAndCurrencyMock = mock(Tuple.class);

        LocalDate testDate = LocalDate.of(2020, 1, 1);
        Integer testLatestPrice = 1000;
        Double testAveragePrice = 2850.00;

        when(storeAndDateAndPriceMock.get(eq(0), eq(Item.class)))
                .thenReturn(testItem);
        when(storeAndDateAndPriceMock.get(eq(1), eq(Store.class)))
                .thenReturn(testStore);
        when(storeAndDateAndPriceMock.get(eq(2), eq(LocalDate.class)))
                .thenReturn(testDate);
        when(storeAndDateAndPriceMock.get(eq(3), eq(Integer.class)))
                .thenReturn(testLatestPrice);

        when(averagePriceAndCurrencyMock.get(eq(0), eq(Item.class)))
                .thenReturn(testItem);
        when(averagePriceAndCurrencyMock.get(eq(1), eq(Double.class)))
                .thenReturn(testAveragePrice);
        when(averagePriceAndCurrencyMock.get(eq(2), eq(String.class)))
                .thenReturn(testCurrency);

        List<Tuple> averageTuples = new ArrayList<>();
        averageTuples.add(averagePriceAndCurrencyMock);

        when(itemRepository.existsById(eq(1L)))
                .thenReturn(true);
        when(itemRepository.findLatestStoreAndShoppingDateAndPrice(anyLong(), eq(1L)))
                .thenReturn(Optional.of(storeAndDateAndPriceMock));

        when(itemRepository.findAverageUnitPriceAndCurrency(anyLong(), eq(1L)))
                .thenReturn(averageTuples);

        //when
        Optional<ItemAnalyticsDto> result = subject.getAnalyticsFor(1L, 1L);

        //then
        assertTrue(result.isPresent());
        ItemAnalyticsDto dto = result.get();

        assertEquals(testStore.getName(), dto.getLatestStore());
        assertEquals(testDate.toString(), dto.getLatestDate());

        assertEquals("$28.50 MXN", dto.getAveragePrice());
        assertEquals("$10.00 MXN", dto.getLatestPrice());

        FrontItem frontItem = dto.getItem();
        assertNotNull(frontItem);

        FrontBrand frontBrand = frontItem.getBrand();
        assertNotNull(frontBrand);
        assertEquals(testBrand.getName(), frontBrand.getName());
        assertEquals(testBrand.getId(), frontBrand.getId());

        FrontCategory frontCategory = frontItem.getCategory();
        assertNotNull(frontCategory);
        assertEquals(testCategory.getName(), frontCategory.getName());
        assertEquals(testCategory.getId(), frontCategory.getId());
    }

    @Test
    public void shouldReturn_listOfDtos_whenCategoryIdExists() {
        //given
        List<Item> items = new ArrayList<>();
        Item testItemB = new Item();
        testItemB.setName("Test Item B");
        testItemB.setId(2L);

        Item testItemC = new Item();
        testItemC.setName("Test Item C");
        testItemC.setId(3L);

        testBrand.addItem(testItemB);
        testBrand.addItem(testItemC);

        Category testCategoryBC = new Category();
        testCategoryBC.setName("Test Category BC");
        testCategoryBC.setId(2L);
        testCategoryBC.addItem(testItemB);
        testCategoryBC.addItem(testItemC);

        items.add(testItem);
        items.add(testItemB);
        items.add(testItemC);

        List<LocalDate> latestDates = new ArrayList<>();
        latestDates.add(LocalDate.of(2020, 2, 28));
        latestDates.add(LocalDate.of(2020, 1, 12));
        latestDates.add(LocalDate.of(2020, 3, 5));

        List<Integer> latestPrices = new ArrayList<>();
        latestPrices.add(2000);
        latestPrices.add(3000);
        latestPrices.add(4000);

        List<Double> averagePrices = new ArrayList<>();
        averagePrices.add(1500.0);
        averagePrices.add(2350.0);
        averagePrices.add(4500.0);

        List<Tuple> averageTuples = getValidAverageTuples(items, averagePrices, testCurrency);
        List<Tuple> latestTuples = getValidLatestTuples(items, testStore, latestDates, latestPrices);

        when(itemRepository.findAverageUnitPriceAndCurrencyForCategory(anyLong(), eq(1L)))
                .thenReturn(averageTuples);
        when(itemRepository.findLatestStoreAndShoppingDateAndPriceForCategory(anyLong(), eq(1L)))
                .thenReturn(latestTuples);

        //when
        List<ItemAnalyticsDto> results = subject.getAnalyticsForCategory(1L, 1);

        //then
        //then
        assertDtoResult(results, items, latestPrices, latestDates, averagePrices, testStore);
    }

    @Test
    public void shouldReturn_null_whenCategoryIdDoesNotExist() {
        //given
        when(itemRepository.findAverageUnitPriceAndCurrencyForCategory(eq(Long.MAX_VALUE), eq(1L)))
                .thenReturn(null);

        when(itemRepository.findLatestStoreAndShoppingDateAndPriceForCategory(eq(Long.MAX_VALUE), eq(1L)))
                .thenReturn(null);

        //when
        List<ItemAnalyticsDto> dtos = subject.getAnalyticsForCategory(Long.MAX_VALUE, 1);

        //then
        assertNull(dtos);
    }

    @Test
    public void shouldReturn_null_whenCategoryIsEmpty() {
        //given
        when(itemRepository.findAverageUnitPriceAndCurrencyForCategory(eq(Long.MAX_VALUE), eq(1L)))
                .thenReturn(Collections.emptyList());

        when(itemRepository.findLatestStoreAndShoppingDateAndPriceForCategory(eq(Long.MAX_VALUE), eq(1L)))
                .thenReturn(Collections.emptyList());

        //when
        List<ItemAnalyticsDto> dtos = subject.getAnalyticsForCategory(Long.MAX_VALUE, 1);

        //then
        assertNull(dtos);
    }

    @Test
    public void shouldReturn_listOfDtos_whenBrandIdExists() {
        //given
        List<Item> items = new ArrayList<>();
        Item testItemB = new Item();
        testItemB.setName("Test Item B");
        testItemB.setId(2L);

        Item testItemC = new Item();
        testItemC.setName("Test Item C");
        testItemC.setId(3L);

        testBrand.addItem(testItemB);
        testBrand.addItem(testItemC);

        Category testCategoryBC = new Category();
        testCategoryBC.setName("Test Category BC");
        testCategoryBC.setId(2L);
        testCategoryBC.addItem(testItemB);
        testCategoryBC.addItem(testItemC);

        items.add(testItem);
        items.add(testItemB);
        items.add(testItemC);

        List<LocalDate> latestDates = new ArrayList<>();
        latestDates.add(LocalDate.of(2020, 2, 28));
        latestDates.add(LocalDate.of(2020, 1, 12));
        latestDates.add(LocalDate.of(2020, 3, 5));

        List<Integer> latestPrices = new ArrayList<>();
        latestPrices.add(2000);
        latestPrices.add(3000);
        latestPrices.add(4000);

        List<Double> averagePrices = new ArrayList<>();
        averagePrices.add(1500.0);
        averagePrices.add(2350.0);
        averagePrices.add(4500.0);

        List<Tuple> averageTuples = getValidAverageTuples(items, averagePrices, testCurrency);
        List<Tuple> latestTuples = getValidLatestTuples(items, testStore, latestDates, latestPrices);

        when(itemRepository.findAverageUnitPriceAndCurrencyForBrand(anyLong(), eq(1L)))
                .thenReturn(averageTuples);
        when(itemRepository.findLatestStoreAndShoppingDateAndPriceForBrand(anyLong(), eq(1L)))
                .thenReturn(latestTuples);

        //when
        List<ItemAnalyticsDto> results = subject.getAnalyticsForBrand(1L, 1);

        //then
        assertDtoResult(results, items, latestPrices, latestDates, averagePrices, testStore);
    }


    @Test
    public void shouldReturn_null_whenBrandIdDoesNotExists() {
        //given
        when(itemRepository.findAverageUnitPriceAndCurrencyForBrand(eq(Long.MAX_VALUE), eq(1L)))
                .thenReturn(null);

        when(itemRepository.findLatestStoreAndShoppingDateAndPriceForBrand(eq(Long.MAX_VALUE), eq(1L)))
                .thenReturn(null);

        //when
        List<ItemAnalyticsDto> dtos = subject.getAnalyticsForBrand(Long.MAX_VALUE, 1);

        //then
        assertNull(dtos);
    }

    @Test
    public void shouldReturn_null_whenBrandIsEmpty() {
        //given
        when(itemRepository.findAverageUnitPriceAndCurrencyForBrand(eq(Long.MAX_VALUE), eq(1L)))
                .thenReturn(Collections.emptyList());

        when(itemRepository.findLatestStoreAndShoppingDateAndPriceForBrand(eq(Long.MAX_VALUE), eq(1L)))
                .thenReturn(Collections.emptyList());

        //when
        List<ItemAnalyticsDto> dtos = subject.getAnalyticsForBrand(Long.MAX_VALUE, 1);

        //then
        assertNull(dtos);
    }

    private void assertDtoResult(List<ItemAnalyticsDto> results,
                                 List<Item> items,
                                 List<Integer> latestPrices,
                                 List<LocalDate> latestDates,
                                 List<Double> averagePrices,
                                 Store store) {
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(3, results.size());
        results = results.stream()
                .sorted((o1, o2) -> {
                    if (o2 == null)
                        return 1;

                    FrontItem dto1 = o1.getItem();
                    FrontItem dto2 = o2.getItem();

                    if (dto2 == null)
                        return 1;
                    return (int) (dto1.getId() - dto2.getId());
                })
                .collect(Collectors.toList());

        for (int i = 0; i < results.size(); ++i) {
            ItemAnalyticsDto dto = results.get(i);
            assertNotNull(dto);

            FrontItem frontItem = dto.getItem();
            assertNotNull(frontItem);

            Item item = items.get(i);
            Integer latestPrice = latestPrices.get(i);
            LocalDate latestDate = latestDates.get(i);
            Double averagePrice = averagePrices.get(i);

            assertDto(dto, item, testStore, latestPrice, latestDate, averagePrice, testCurrency);
        }
    }

    private void assertDto(ItemAnalyticsDto analyticsDto,
                           Item item,
                           Store store,
                           Integer latestPrice,
                           LocalDate latestDate,
                           Double averagePrice,
                           String currency) {
        String errorMessage = String.format("Test failed for Item with id: %d", item.getId());

        FrontItem frontItem = analyticsDto.getItem();
        assertEquals(item.getName(), frontItem.getName(), errorMessage);

        Category category = item.getCategory();
        FrontCategory frontCategory = frontItem.getCategory();
        assertNotNull(frontCategory, errorMessage);
        assertEquals(category.getName(), frontCategory.getName(), errorMessage);
        assertEquals(category.getId(), frontCategory.getId(), errorMessage);

        Brand brand = item.getBrand();
        FrontBrand frontBrand = frontItem.getBrand();
        assertNotNull(frontBrand, errorMessage);
        assertEquals(brand.getName(), frontBrand.getName(), errorMessage);
        assertEquals(brand.getId(), frontBrand.getId(), errorMessage);

        assertEquals(store.getName(), analyticsDto.getLatestStore(), errorMessage);
        String formattedLatestPrice = FrontShoppingItem.transformAndFormatPrice(latestPrice, currency);
        assertEquals(formattedLatestPrice, analyticsDto.getLatestPrice(), errorMessage);
        assertEquals(latestDate.toString(), analyticsDto.getLatestDate(), errorMessage);
        String formattedAveragePrice = FrontShoppingItem.transformAndFormatPrice(averagePrice, currency);
        assertEquals(formattedAveragePrice, analyticsDto.getAveragePrice());

    }

    private List<Tuple> getValidLatestTuples(List<Item> items,
                                             Store store,
                                             List<LocalDate> latestDates,
                                             List<Integer> latestPrices) {
        List<Tuple> latestTuples = new ArrayList<>();

        Tuple latestTupleA = mock(Tuple.class);
        Tuple latestTupleB = mock(Tuple.class);
        Tuple latestTupleC = mock(Tuple.class);

        latestTuples.add(latestTupleA);
        latestTuples.add(latestTupleB);
        latestTuples.add(latestTupleC);

        for (int i = 0; i < latestTuples.size(); ++i) {
            Tuple latestTuple = latestTuples.get(i);
            Item item = items.get(i);
            LocalDate latestDate = latestDates.get(i);
            Integer latestPrice = latestPrices.get(i);

            when(latestTuple.get(eq(0), eq(Item.class)))
                    .thenReturn(item);
            when(latestTuple.get(eq(1), eq(Store.class)))
                    .thenReturn(store);
            when(latestTuple.get(eq(2), eq(LocalDate.class)))
                    .thenReturn(latestDate);
            when(latestTuple.get(eq(3), eq(Integer.class)))
                    .thenReturn(latestPrice);
        }


        return latestTuples;
    }

    private List<Tuple> getValidAverageTuples(List<Item> items,
                                              List<Double> averagePrices,
                                              String currency) {
        List<Tuple> averageTuples = new ArrayList<>();

        Tuple averageTupleA = mock(Tuple.class);
        Tuple averageTupleB = mock(Tuple.class);
        Tuple averageTupleC = mock(Tuple.class);

        averageTuples.add(averageTupleA);
        averageTuples.add(averageTupleB);
        averageTuples.add(averageTupleC);

        for (int i = 0; i < averageTuples.size(); ++i) {
            Tuple tuple = averageTuples.get(i);
            Item item = items.get(i);
            Double averagePrice = averagePrices.get(i);

            when(tuple.get(eq(0), eq(Item.class)))
                    .thenReturn(item);
            when(tuple.get(eq(1), eq(Double.class)))
                    .thenReturn(averagePrice);
            when(tuple.get(eq(2), eq(String.class)))
                    .thenReturn(currency);
        }

        return averageTuples;
    }
}
