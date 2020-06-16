package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
public class JpaTest {

    private final ShoppingListRepository shoppingListRepository;
    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    private Brand testBrand;
    private Category testCategory;
    private Store testStore;
    private Item testItem;

    @Autowired
    public JpaTest(ShoppingListRepository shoppingListRepository,
                   StoreRepository storeRepository,
                   ItemRepository itemRepository,
                   BrandRepository brandRepository,
                   CategoryRepository categoryRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.storeRepository = storeRepository;
        this.itemRepository = itemRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @BeforeEach
    @SuppressWarnings("unused")
    public void setup() {
        testBrand = new Brand();
        testBrand.setName("Test Brand");

        testCategory = new Category();
        testCategory.setName("Test Category");

        testStore = new Store();
        testStore.setName("Test Store");
        storeRepository.save(testStore);

        testItem = new Item();
        testItem.setName("Test item");

        testBrand.addItem(testItem);
        testCategory.addItem(testItem);

        brandRepository.save(testBrand);
        categoryRepository.save(testCategory);
        itemRepository.save(testItem);
    }

    @Test
    public void shouldInsert_newShoppingList_with_oneItem() {
        long testItemId = testItem.getId();
        ShoppingList shoppingList = new ShoppingList();
        ShoppingItem shoppingItem = new ShoppingItem();

        shoppingList.setShoppingDate(LocalDate.now());
        testStore.addShoppingList(shoppingList);

        shoppingItem.setCurrency("MXN");
        shoppingItem.setUnitPrice(100);
        testItem.addShoppingItem(shoppingItem);
        shoppingList.addShoppingItem(shoppingItem);

        shoppingListRepository.save(shoppingList);
        assertTrue(shoppingList.getId() > 0);
        assertTrue(shoppingItem.getId() > 0);
        assertEquals(itemRepository.findById(testItemId).orElse(null),
                shoppingItem.getItem());
    }

}
