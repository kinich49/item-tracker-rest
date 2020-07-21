package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.database.Item;
import mx.kinich49.itemtracker.models.database.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.Collection;
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
    @DisplayName("Get valid Latest Store, Price and Date for item with id 1 and user id 1L")
    public void findLatestTuple_forItemId1_userId1() {
        //when
        Optional<Tuple> response = itemRepository.findLatestStoreAndShoppingDateAndPrice(1, 1L);

        //then
        assertNotNull(response);
        assertTrue(response.isPresent());

        Tuple tuple = response.get();
        Item item = tuple.get(0, Item.class);
        assertNotNull(item);

        Store latestStore = tuple.get(1, Store.class);
        assertNotNull(latestStore);
        assertEquals(2, latestStore.getId());

        LocalDate latestDate = tuple.get(2, LocalDate.class);
        assertNotNull(latestDate);
        assertEquals("2020-01-26", latestDate.toString());

        Integer latestPrice = tuple.get(3, Integer.class);
        assertNotNull(latestPrice);
        assertEquals(2100, latestPrice.intValue());
    }

    @Test
    @DisplayName("Get valid Latest Store, Price and Date for item with id 1 and user id 2L")
    public void findLatestTuple_forItemId1_userId2() {
        //when
        Optional<Tuple> response = itemRepository.findLatestStoreAndShoppingDateAndPrice(1, 2);

        //then
        assertNotNull(response);
        assertTrue(response.isPresent());

        Tuple tuple = response.get();
        Item item = tuple.get(0, Item.class);
        assertNotNull(item);

        Store latestStore = tuple.get(1, Store.class);
        assertNotNull(latestStore);
        assertEquals(3, latestStore.getId());

        LocalDate latestDate = tuple.get(2, LocalDate.class);
        assertNotNull(latestDate);
        assertEquals("2020-02-23", latestDate.toString());

        Integer latestPrice = tuple.get(3, Integer.class);
        assertNotNull(latestPrice);
        assertEquals(3500, latestPrice.intValue());
    }

    @Test
    @DisplayName("Get valid Latest Store, Price and Date for item with id 4 for user id 1")
    public void shouldReturn_LatestTuple_forItemId4() {
        //when
        Optional<Tuple> response = itemRepository.findLatestStoreAndShoppingDateAndPrice(4, 1L);

        //then
        assertNotNull(response);
        assertTrue(response.isPresent());

        Tuple tuple = response.get();
        Item item = tuple.get(0, Item.class);
        assertNotNull(item);

        Store latestStore = tuple.get(1, Store.class);
        assertNotNull(latestStore);
        assertEquals(3, latestStore.getId());

        LocalDate latestDate = tuple.get(2, LocalDate.class);
        assertNotNull(latestDate);
        assertEquals("2020-02-02", latestDate.toString());

        Integer latestPrice = tuple.get(3, Integer.class);
        assertNotNull(latestPrice);
        assertEquals(1000, latestPrice.intValue());
    }

    @Test
    @DisplayName("Optional Tuple must be empty when item id does not exists")
    public void shouldReturn_emptyTuple_forInvalidItemId() {
        //when
        Optional<Tuple> response = itemRepository.findLatestStoreAndShoppingDateAndPrice(Long.MAX_VALUE, 1L);

        assertNotNull(response);
        assertFalse(response.isPresent());
    }

    @Test
    @DisplayName("Get average price for item with valid id")
    public void findAveragePrice() {
        //when
        List<Tuple> results = itemRepository.findAverageUnitPriceAndCurrency(1, 1L);

        //then
        assertNotNull(results);
        assertFalse(results.isEmpty());

        Tuple result = results.get(0);
        Item item = result.get(0, Item.class);
        assertNotNull(item);

        Double averagePrice = result.get(1, Double.class);
        assertEquals(2250, averagePrice);

        String currency = result.get(2, String.class);
        assertEquals("MXN", currency);
    }

    @Test
    @DisplayName("Get latest store, date and unit price for all items within a category")
    public void shouldReturn_latestDataTuple_whenCategoryIdIsValid() {
        //when
        List<Tuple> results = itemRepository.findLatestStoreAndShoppingDateAndPriceForCategory(1L, 1L);

        //then
        assertNotNull(results);
        assertEquals(4, results.size());

        assertLatestDataTuple(results.get(0), 1, 2100,
                LocalDate.of(2020, 1, 26), 2);

        assertLatestDataTuple(results.get(1), 2, 5000,
                LocalDate.of(2020, 1, 12), 2);

        assertLatestDataTuple(results.get(2), 3, 1000,
                LocalDate.of(2020, 1, 12), 2);

        assertLatestDataTuple(results.get(3), 4, 1000,
                LocalDate.of(2020, 2, 2), 3);
    }

    @Test
    @DisplayName("Optional Tuple must be empty when category id does not exists")
    public void shouldReturn_emptyLatestDataTuple_whenCategoryIdIdNotValid() {
        //when
        List<Tuple> results = itemRepository
                .findLatestStoreAndShoppingDateAndPriceForCategory(Long.MAX_VALUE, 1L);

        //then
        assertNull(results);
    }

    @Test
    @DisplayName("Optional Tuple must be empty when category has no elements")
    public void shouldReturn_emptyLatestDataTuple_whenCategoryIsEmpty() {
        //when
        List<Tuple> results = itemRepository
                .findLatestStoreAndShoppingDateAndPriceForCategory(4, 1L);

        //then
        assertNull(results);
    }

    @Test
    @DisplayName("Get Average Prices for items in valid category")
    public void shouldReturn_listOfAverageTuple_whenCategoryIsValid() {
        //when
        List<Tuple> results = itemRepository.findAverageUnitPriceAndCurrencyForCategory(1L, 1L);

        //then
        assertNotNull(results);
        assertEquals(4, results.size());
        String expectedCurrency = "MXN";
        assertAverageTuple(results.get(0), 1L, 2250, expectedCurrency);
        assertAverageTuple(results.get(1), 2L, 5000, expectedCurrency);
        assertAverageTuple(results.get(2), 3L, 1000, expectedCurrency);
        assertAverageTuple(results.get(3), 4L, 1000, expectedCurrency);
    }

    @Test
    @DisplayName("Get latest store, date and unit price for all items within a brand")
    public void shouldReturn_latestDataTuple_whenBrandIdIsValid() {
        //when
        List<Tuple> results = itemRepository.findLatestStoreAndShoppingDateAndPriceForBrand(3L, 1L);

        //then
        assertNotNull(results);
        assertEquals(2, results.size());

        assertLatestDataTuple(results.get(0), 2, 5000,
                LocalDate.of(2020, 1, 12), 2);

        assertLatestDataTuple(results.get(1), 6, 1300,
                LocalDate.of(2020, 1, 19), 1);
    }

    @Test
    @DisplayName("Get Average Prices for items in valid brand")
    public void shouldReturn_listOfAverageTuple_whenBrandIsValid() {
        //when
        List<Tuple> results = itemRepository.findAverageUnitPriceAndCurrencyForBrand(4L, 1L);

        //then
        assertNotNull(results);
        assertEquals(3, results.size());

        String expectedCurrency = "MXN";
        assertAverageTuple(results.get(0), 4L, 1000, expectedCurrency);
        assertAverageTuple(results.get(1), 7L, 1000, expectedCurrency);
        assertAverageTuple(results.get(2), 8L, 4750, expectedCurrency);
    }


    @Test
    @DisplayName("Optional Tuple must be empty when brand has no elements")
    public void shouldReturn_emptyLatestDataTuple_whenBrandIsEmpty() {
        //when
        Collection<Tuple> results = itemRepository
                .findLatestStoreAndShoppingDateAndPriceForBrand(5, 1L);

        //then
        assertNull(results);
    }

    @Test
    @DisplayName("Optional Tuple must be empty when brand id does not exists")
    public void shouldReturn_emptyLatestDataTuple_whenBrandIdIsNotValid() {
        //when
        Collection<Tuple> results = itemRepository
                .findLatestStoreAndShoppingDateAndPriceForBrand(Long.MAX_VALUE, 1L);

        //then
        assertNull(results);
    }

    private void assertLatestDataTuple(Tuple tuple,
                                       long expectedItemId,
                                       int expectedPrice,
                                       LocalDate expectedDate,
                                       long expectedStoreId) {
        String errorMessage = String.format("Test failed for expected Item Id: %d", expectedItemId);
        assertNotNull(tuple, errorMessage);

        Item item = tuple.get(0, Item.class);
        assertNotNull(item);
        assertEquals(expectedItemId, item.getId());

        Store store = tuple.get(1, Store.class);
        assertNotNull(store, errorMessage);
        assertEquals(expectedStoreId, store.getId(), errorMessage);

        LocalDate localDate = tuple.get(2, LocalDate.class);
        assertNotNull(localDate, errorMessage);
        assertEquals(expectedDate.toString(), localDate.toString(), errorMessage);

        Integer unitPrice = tuple.get(3, Integer.class);
        assertEquals(expectedPrice, unitPrice, errorMessage);
    }

    private void assertAverageTuple(Tuple tuple,
                                    long expectedItemId,
                                    double expectedAverageUnitPrice,
                                    String expectedCurrency) {
        String errorMessage = String.format("Test failed for expected Item Id: %d", expectedItemId);
        assertNotNull(tuple, errorMessage);

        Item item = tuple.get(0, Item.class);
        assertNotNull(item, errorMessage);
        assertEquals(expectedItemId, item.getId(), errorMessage);

        Double averageUnitPrice = tuple.get(1, Double.class);
        assertNotNull(averageUnitPrice, errorMessage);
        assertEquals(expectedAverageUnitPrice, averageUnitPrice, errorMessage);

        String currency = tuple.get(2, String.class);
        assertNotNull(currency, errorMessage);
        assertEquals(expectedCurrency, currency, errorMessage);
    }
}
