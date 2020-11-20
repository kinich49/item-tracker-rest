package mx.kinich49.itemtracker.models.front;

import mx.kinich49.itemtracker.models.database.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FrontShoppingItemTest {

    Brand testBrand;
    Category testCategory;
    Store testStore;
    Item testItem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        testBrand = new Brand();
        testBrand.setId(1L);
        testBrand.setName("Test Brand");

        testCategory = new Category();
        testCategory.setId(1L);
        testCategory.setName("Test Category");

        testStore = new Store();
        testStore.setName("Test Store");
        testStore.setId(1L);

        testItem = new Item();
        testItem.setId(1L);
        testItem.setName("Test Item");
        testBrand.addItem(testItem);
        testCategory.addItem(testItem);
    }

    @Test
    @DisplayName("Price must be $10.50 USD")
    public void should_print10dot50Price() {
        //given
        ShoppingItem shoppingItem = new ShoppingItem();
        testItem.addShoppingItem(shoppingItem);

        shoppingItem.setUnitPrice(1050);
        shoppingItem.setCurrency("USD");

        //when
        FrontShoppingItem result = FrontShoppingItem.from(shoppingItem);

        //then
        assertNotNull(result);
        assertEquals("$10.50 USD", result.getUnitPrice());
    }

    @Test
    @DisplayName("Price must be $10.00 MXN")
    public void should_print10MXNPrice() {
        //given
        ShoppingItem shoppingItem = new ShoppingItem();
        testItem.addShoppingItem(shoppingItem);

        shoppingItem.setUnitPrice(1000);
        shoppingItem.setCurrency("MXN");

        //when
        FrontShoppingItem result = FrontShoppingItem.from(shoppingItem);

        //then
        assertNotNull(result);
        assertEquals("$10.00 MXN", result.getUnitPrice());
    }

    @Test
    @DisplayName("Price must be $0.50 GBP")
    public void should_printDot50BPPrice() {
        //given
        ShoppingItem shoppingItem = new ShoppingItem();
        testItem.addShoppingItem(shoppingItem);

        shoppingItem.setUnitPrice(50);
        shoppingItem.setCurrency("GBP");

        //when
        FrontShoppingItem result = FrontShoppingItem.from(shoppingItem);

        //then
        assertNotNull(result);
        assertEquals("$0.50 GBP", result.getUnitPrice());
    }

    @Test
    @DisplayName("Price must be $1,000.00 YEN")
    public void should_print1000YENPrice() {
        //given
        ShoppingItem shoppingItem = new ShoppingItem();
        testItem.addShoppingItem(shoppingItem);

        shoppingItem.setUnitPrice(100000);
        shoppingItem.setCurrency("YEN");

        //when
        FrontShoppingItem result = FrontShoppingItem.from(shoppingItem);

        //then
        assertNotNull(result);
        assertEquals("$1,000.00 YEN", result.getUnitPrice());
    }

    @Test
    @DisplayName("Quantity must be 0.485 KG")
    public void should_printZeroDot485KGQuantity() {
        //given
        ShoppingItem shoppingItem = new ShoppingItem();
        testItem.addShoppingItem(shoppingItem);

        shoppingItem.setUnit("KG");
        shoppingItem.setQuantity(0.485);

        //when
        FrontShoppingItem result = FrontShoppingItem.from(shoppingItem);

        //then
        assertNotNull(result);
        assertEquals("0.485 KG", result.getQuantity());
    }

    @Test
    @DisplayName("Quantity must be 1.0 KG")
    public void should_print1DotZeroKGQuantity() {
        //given
        ShoppingItem shoppingItem = new ShoppingItem();
        testItem.addShoppingItem(shoppingItem);

        shoppingItem.setUnit("KG");
        shoppingItem.setQuantity(1);

        //when
        FrontShoppingItem result = FrontShoppingItem.from(shoppingItem);

        //then
        assertNotNull(result);
        assertEquals("1.0 KG", result.getQuantity());
    }

    @Test
    @DisplayName("Quantity must be 1")
    public void should_print1Quantity() {
        //given
        ShoppingItem shoppingItem = new ShoppingItem();
        testItem.addShoppingItem(shoppingItem);

        shoppingItem.setQuantity(1);

        //when
        FrontShoppingItem result = FrontShoppingItem.from(shoppingItem);

        //then
        assertNotNull(result);
        assertEquals("1", result.getQuantity());
    }

    @Test
    @DisplayName("Quantity must be 1,000")
    public void should_print1comma000Quantity() {
        //given
        ShoppingItem shoppingItem = new ShoppingItem();
        testItem.addShoppingItem(shoppingItem);

        shoppingItem.setQuantity(1000);

        //when
        FrontShoppingItem result = FrontShoppingItem.from(shoppingItem);

        //then
        assertNotNull(result);
        assertEquals("1,000", result.getQuantity());
    }
}
