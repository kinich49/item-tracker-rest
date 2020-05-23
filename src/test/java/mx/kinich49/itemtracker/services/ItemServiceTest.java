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
import org.junit.jupiter.api.Disabled;
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
    @Mock
    SuggestionService suggestionService;

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
