package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = {"test"})
public class ItemRepositoryCustomTest {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemRepositoryCustomTest(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Test
    @DisplayName("Get valid Latest Store, Price and Date for item 1")
    public void findLatestStoreAndShoppingDateAndPrice_forItemId1() {
        //when
        Optional<Tuple> response = itemRepository.findLatestStoreAndShoppingDateAndPrice(1);

        //then
        assertNotNull(response);
        assertTrue(response.isPresent());
        Tuple tuple = response.get();
        Store latestStore = tuple.get(0, Store.class);
        assertNotNull(latestStore);
        assertEquals(2, latestStore.getId());
        LocalDate latestDate = tuple.get(1, LocalDate.class);
        assertNotNull(latestDate);
        assertEquals("2020-01-26", latestDate.toString());
        Integer latestPrice = tuple.get(2, Integer.class);
        assertNotNull(latestPrice);
        assertEquals(2100, latestPrice.intValue());
    }

    @Test
    @DisplayName("Get valid Latest Store, Price and Date for item 4")
    public void shouldReturn_LatestStoreAndShoppingDateAndPrice_forItemId4() {
        //when
        Optional<Tuple> response = itemRepository.findLatestStoreAndShoppingDateAndPrice(4);

        //then
        assertNotNull(response);
        assertTrue(response.isPresent());
        Tuple tuple = response.get();
        Store latestStore = tuple.get(0, Store.class);
        assertNotNull(latestStore);
        assertEquals(3, latestStore.getId());
        LocalDate latestDate = tuple.get(1, LocalDate.class);
        assertNotNull(latestDate);
        assertEquals("2020-02-02", latestDate.toString());
        Integer latestPrice = tuple.get(2, Integer.class);
        assertNotNull(latestPrice);
        assertEquals(1000, latestPrice.intValue());
    }

    @Test
    @DisplayName("Optional must be empty if item id does not exists")
    public void shouldReturn_emptyTuple_forInvalidItemId() {
        //when
        Optional<Tuple> response = itemRepository.findLatestStoreAndShoppingDateAndPrice(Integer.MAX_VALUE);

        assertNotNull(response);
        assertFalse(response.isPresent());
    }

    @Test
    @DisplayName("Get average price for item")
    public void findAveragePrice() {
        //when
        List<Tuple> results = itemRepository.findAverageUnitPriceAndCurrency(1);

        //then
        assertNotNull(results);
        assertFalse(results.isEmpty());
        Tuple result = results.get(0);
        Double averagePrice = result.get(0, Double.class);
        assertEquals(2250, averagePrice);
        String currency = result.get(1, String.class);
        assertEquals("MXN", currency);
    }

    @Test
    @DisplayName("Get latest store, date and unit price for all items within a category")
    public void findLatestStoreAndDateForCategory() {
        Optional<List<Tuple>> optResults = itemRepository.findLatestStoreAndShoppingDateAndPriceForCategory(1L);

        assertTrue(optResults.isPresent());
        List<Tuple> results = optResults.get();
        assertEquals(4, results.size());

        assertTuple(results.get(0), 1, 2100, LocalDate.of(2020, 1, 26), 2);
        assertTuple(results.get(1), 2, 5000, LocalDate.of(2020, 1, 19), 1);
        assertTuple(results.get(2), 3, 1000, LocalDate.of(2020, 1, 19), 1);
        assertTuple(results.get(3), 4, 1000, LocalDate.of(2020, 2, 2), 3);
    }

    @Test
    @DisplayName("Optional must be empty when category id does not exists")
    public void shouldReturn_emptyTuple_whenCategoryIdIdNotValid() {
        Optional<List<Tuple>> optResults = itemRepository
                .findLatestStoreAndShoppingDateAndPriceForCategory(Long.MAX_VALUE);

        assertFalse(optResults.isPresent());
    }

    @Test
    @DisplayName("Optional must be empty when category is empty")
    public void shouldReturn_emptyTuple_whenCategoryIsEmpty() {
        Optional<List<Tuple>> optResults = itemRepository
                .findLatestStoreAndShoppingDateAndPriceForCategory(4);

        assertFalse(optResults.isPresent());
    }

    private void assertTuple(Tuple tuple,
                             long expectedItemId,
                             int expectedPrice,
                             LocalDate expectedDate,
                             long expectedStoreId) {
        assertNotNull(tuple);
        Store store = tuple.get(0, Store.class);
        assertNotNull(store);
        assertEquals(expectedStoreId, store.getId());
        LocalDate localDate = tuple.get(1, LocalDate.class);
        assertNotNull(localDate);
        assertEquals(expectedDate.toString(), localDate.toString());
        Integer unitPrice = tuple.get(2, Integer.class);
        assertEquals(expectedPrice, unitPrice);
        Long itemId = tuple.get(3, Long.class);
        assertEquals(expectedItemId, itemId);
    }
}
