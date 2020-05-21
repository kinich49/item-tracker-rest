package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ItemRepositoryCustomTest {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemRepositoryCustomTest(ItemRepository shoppingItemRepository) {
        this.itemRepository = shoppingItemRepository;
    }

    @Test
    @DisplayName("Get valid Latest Store, Price and Date for item")
    public void findAnalyticsForItem() {
        //when
        Optional<Tuple> response = itemRepository.findLatestStoreAndShoppingDateAndPrice(11);

        //then
        assertNotNull(response);
        assertTrue(response.isPresent());
        Tuple tuple = response.get();
        Store latestStore = tuple.get(0, Store.class);
        assertNotNull(latestStore);
        assertEquals(3, latestStore.getId());
        LocalDate latestDate = tuple.get(1, LocalDate.class);
        assertNotNull(latestDate);
        assertEquals(latestDate.toString(), "2020-05-20");
        Integer latestPrice = tuple.get(2, Integer.class);
        assertNotNull(latestPrice);
        assertEquals(8000, latestPrice.intValue());
    }


    @Test
    @DisplayName("Get average price for item")
    public void findAveragePrice() {
        //when
        List<Tuple> results = itemRepository.findAverageUnitPriceAndCurrency(11);

        //then
        assertNotNull(results);
        assertFalse(results.isEmpty());
        Tuple result = results.get(0);
        Double averagePrice = result.get(0, Double.class);
        assertEquals(7100, averagePrice);
        String currency = result.get(1, String.class);
        assertEquals("MXN", currency);
    }
}
