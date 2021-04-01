package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.models.database.Brand;
import mx.kinich49.itemtracker.models.database.Category;
import mx.kinich49.itemtracker.models.database.Item;
import mx.kinich49.itemtracker.models.front.FrontShoppingItem;
import mx.kinich49.itemtracker.models.front.FrontShoppingList;
import mx.kinich49.itemtracker.models.front.FrontStore;
import mx.kinich49.itemtracker.repositories.ItemRepository;
import mx.kinich49.itemtracker.requests.main.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles({"test"})
public class ShoppingServiceIntegrationTest {

    private final ShoppingService subject;
    private final ItemRepository itemRepository;

    @Autowired
    public ShoppingServiceIntegrationTest(ShoppingService subject, ItemRepository itemRepository) {
        this.subject = subject;
        this.itemRepository = itemRepository;
    }

    /**
     * This test tries to insert two new items.
     * Those items have the same new Brand.
     * The brand still has no id at that moment.
     * <p>
     * Each item has a different already persisted Category
     * <p>
     * When persisted, the items must have the same brand id.
     *
     */
    @DisplayName("Persist two new Items with the same new Brand")
    @Test
    public void insert_twoNewItemsWithSameNewBrand() throws BusinessException {
        //given
        MainShoppingListRequest shoppingListRequest = new MainShoppingListRequest();
        shoppingListRequest.setShoppingDate(LocalDate.now());
        shoppingListRequest.setUserId(1L);

        String itemName_A = "Test Item new Brand A";
        String itemName_B = "Test Item new Brand B";

        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setId(1L);
        shoppingListRequest.setStore(storeRequest);

        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Test Brand New");

        CategoryRequest categoryRequest_A = new CategoryRequest();
        categoryRequest_A.setId(1L);

        CategoryRequest categoryRequest_B = new CategoryRequest();
        categoryRequest_B.setId(2L);

        MainShoppingItemRequest shoppingItem_A = new MainShoppingItemRequest();
        shoppingItem_A.setBrand(brandRequest);
        shoppingItem_A.setCategory(categoryRequest_A);
        shoppingItem_A.setCurrency("MXN");
        shoppingItem_A.setName(itemName_A);
        shoppingItem_A.setQuantity(1);
        shoppingItem_A.setUnit("KG");
        shoppingItem_A.setUnitPrice(15);

        MainShoppingItemRequest shoppingItem_B = new MainShoppingItemRequest();
        shoppingItem_B.setBrand(brandRequest);
        shoppingItem_B.setCategory(categoryRequest_B);
        shoppingItem_B.setCurrency("MXN");
        shoppingItem_B.setName(itemName_B);
        shoppingItem_B.setQuantity(2);
        shoppingItem_B.setUnit("Unit");
        shoppingItem_B.setUnitPrice(25);

        List<MainShoppingItemRequest> shoppingItemRequests = new ArrayList<>();
        shoppingItemRequests.add(shoppingItem_A);
        shoppingItemRequests.add(shoppingItem_B);
        shoppingListRequest.setShoppingItems(shoppingItemRequests);

        //when
        subject.save(shoppingListRequest);

        //then
        Optional<Item> result_A = itemRepository.findByName(itemName_A);
        assertTrue(result_A.isPresent());
        Item resultItem_A = result_A.get();

        Optional<Item> result_B = itemRepository.findByName(itemName_B);
        assertTrue(result_B.isPresent());
        Item itemB = result_B.get();

        Brand brand_A = resultItem_A.getBrand();
        assertNotNull(brand_A);
        Brand brand_B = itemB.getBrand();
        assertNotNull(brand_B);

        assertEquals(brand_A, brand_B);
    }

    /**
     * This test tries to insert two new items.
     * Those items have the same new Category.
     * The category still has no id at that moment.
     * <p>
     * Each item has a different already persisted brand
     * <p>
     * When persisted, the items must have the same category id.
     *
     */
    @DisplayName("Persist two new Items with the same new Category")
    @Test
    public void insert_newItems_withSame_newCategory() throws BusinessException{
        //given
        MainShoppingListRequest shoppingListRequest = new MainShoppingListRequest();
        shoppingListRequest.setShoppingDate(LocalDate.now());
        shoppingListRequest.setUserId(1L);

        String itemName_A = "Test Item new Category A";
        String itemName_B = "Test Item new Category B";
        String categoryName = "Test Category new";

        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setId(1L);
        storeRequest.setName("Test Store 1");
        shoppingListRequest.setStore(storeRequest);

        BrandRequest brandRequest_A = new BrandRequest();
        brandRequest_A.setId(1L);

        BrandRequest brandRequest_B = new BrandRequest();
        brandRequest_B.setId(2L);

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName(categoryName);


        MainShoppingItemRequest shoppingItem_A = new MainShoppingItemRequest();
        shoppingItem_A.setBrand(brandRequest_A);
        shoppingItem_A.setCategory(categoryRequest);
        shoppingItem_A.setCurrency("MXN");
        shoppingItem_A.setName(itemName_A);
        shoppingItem_A.setQuantity(1);
        shoppingItem_A.setUnit("KG");
        shoppingItem_A.setUnitPrice(15);

        MainShoppingItemRequest shoppingItem_B = new MainShoppingItemRequest();
        shoppingItem_B.setBrand(brandRequest_B);
        shoppingItem_B.setCategory(categoryRequest);
        shoppingItem_B.setCurrency("MXN");
        shoppingItem_B.setName(itemName_B);
        shoppingItem_B.setQuantity(2);
        shoppingItem_B.setUnit("Unit");
        shoppingItem_B.setUnitPrice(25);

        List<MainShoppingItemRequest> shoppingItemRequests = new ArrayList<>();
        shoppingItemRequests.add(shoppingItem_A);
        shoppingItemRequests.add(shoppingItem_B);
        shoppingListRequest.setShoppingItems(shoppingItemRequests);

        //when
        subject.save(shoppingListRequest);

        //then
        Optional<Item> optionalItem_A = itemRepository.findByName(itemName_A);
        assertTrue(optionalItem_A.isPresent());
        Item resultItem_A = optionalItem_A.get();
        Category category_A = resultItem_A.getCategory();
        assertNotNull(category_A);

        Optional<Item> optionalItem_B = itemRepository.findByName(itemName_B);
        assertTrue(optionalItem_B.isPresent());
        Item resultItem_B = optionalItem_B.get();
        Category category_B = resultItem_B.getCategory();
        assertNotNull(category_B);

        assertEquals(category_A, category_B);
    }

    /**
     * This test tries to insert two new items.
     * those items have the same new Category.
     * and the same new Brand
     * <p>
     * When persisted, the items must have
     * the same category id and the same brand id
     *
     */
    @DisplayName("Persist two new Items with the same new Category and same new Brand")
    @Test
    public void insert_newItems_withSame_newCategory_andSameNewBrand() throws BusinessException{
        //given
        MainShoppingListRequest shoppingListRequest = new MainShoppingListRequest();
        shoppingListRequest.setShoppingDate(LocalDate.now());
        shoppingListRequest.setUserId(1L);

        String itemName_A = "Test Item new Category and Brand A";
        String itemName_B = "Test Item new Category and Brand B";
        String categoryName = "Test Category new A";
        String brandName = "Test Brand new A";

        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setId(1L);
        storeRequest.setName("Test Store 1");
        shoppingListRequest.setStore(storeRequest);

        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName(brandName);

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName(categoryName);

        MainShoppingItemRequest shoppingItem_A = new MainShoppingItemRequest();
        shoppingItem_A.setBrand(brandRequest);
        shoppingItem_A.setCategory(categoryRequest);
        shoppingItem_A.setCurrency("MXN");
        shoppingItem_A.setName(itemName_A);
        shoppingItem_A.setQuantity(1);
        shoppingItem_A.setUnit("KG");
        shoppingItem_A.setUnitPrice(15);

        MainShoppingItemRequest shoppingItem_B = new MainShoppingItemRequest();
        shoppingItem_B.setBrand(brandRequest);
        shoppingItem_B.setCategory(categoryRequest);
        shoppingItem_B.setCurrency("MXN");
        shoppingItem_B.setName(itemName_B);
        shoppingItem_B.setQuantity(2);
        shoppingItem_B.setUnit("Unit");
        shoppingItem_B.setUnitPrice(25);

        List<MainShoppingItemRequest> shoppingItemRequests = new ArrayList<>();
        shoppingItemRequests.add(shoppingItem_A);
        shoppingItemRequests.add(shoppingItem_B);
        shoppingListRequest.setShoppingItems(shoppingItemRequests);

        //when
        subject.save(shoppingListRequest);

        //then
        Optional<Item> optionalItem_A = itemRepository.findByName(itemName_A);
        assertTrue(optionalItem_A.isPresent());
        Item resultItem_A = optionalItem_A.get();
        Brand brand_A = resultItem_A.getBrand();
        assertNotNull(brand_A);
        Category category_A = resultItem_A.getCategory();
        assertNotNull(category_A);

        Optional<Item> optionalItem_B = itemRepository.findByName(itemName_B);
        assertTrue(optionalItem_B.isPresent());
        Item resultItem_B = optionalItem_B.get();
        Brand brand_B = resultItem_B.getBrand();
        assertNotNull(brand_B);
        Category category_B = resultItem_B.getCategory();
        assertNotNull(category_B);

        assertEquals(brand_A, brand_B);
        assertEquals(category_A, category_B);
    }

    /**
     * This test tries to insert an already persisted
     * item bought in a brand new store
     * <p>
     * When persisted, the store must return
     * an id and the item id must be the same
     *
     * @throws Exception when database is empty
     */
    @DisplayName("Persists previous item in a brand new Store")
    @Test
    public void insert_persistedItem_with_newStore() throws Exception {
        MainShoppingListRequest shoppingListRequest = new MainShoppingListRequest();
        shoppingListRequest.setShoppingDate(LocalDate.now());
        shoppingListRequest.setUserId(1L);

        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setName("Brand New Store");
        shoppingListRequest.setStore(storeRequest);

        Item item = itemRepository.findById(5L)
                .orElseThrow();

        Brand brand = Optional.ofNullable(item.getBrand())
                .orElseThrow();

        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName(brand.getName());
        brandRequest.setId(brand.getId());

        Category category = item.getCategory();
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName(category.getName());
        categoryRequest.setId(category.getId());

        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();
        itemRequest.setBrand(brandRequest);
        itemRequest.setCategory(categoryRequest);
        itemRequest.setId(item.getId());
        itemRequest.setCurrency("MXN");
        itemRequest.setUnit("Unit");
        itemRequest.setUnitPrice(20000);
        itemRequest.setQuantity(1);
        itemRequest.setName(item.getName());

        List<MainShoppingItemRequest> itemRequests = new ArrayList<>();
        itemRequests.add(itemRequest);

        shoppingListRequest.setShoppingItems(itemRequests);

        Optional<FrontShoppingList> optionalResult = subject.save(shoppingListRequest);

        assertTrue(optionalResult.isPresent());

        FrontShoppingList result = optionalResult.get();

        assertNotNull(result.getStore());
        FrontStore resultStore = result.getStore();

        assertNotEquals(0, resultStore.getId());

        assertNotNull(result.getItems());
        assertFalse(result.getItems().isEmpty());

        FrontShoppingItem resultItem = result.getItems().get(0);
        assertEquals(item.getName(), resultItem.getName());
        assertEquals(item.getId(), resultItem.getId());

    }
}
