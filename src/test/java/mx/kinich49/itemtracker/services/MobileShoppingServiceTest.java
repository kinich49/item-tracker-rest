package mx.kinich49.itemtracker.services;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.mobile.*;
import mx.kinich49.itemtracker.models.mobile.*;
import mx.kinich49.itemtracker.requests.mobile.*;
import org.assertj.core.annotations.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = {"test"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MobileShoppingServiceTest {

    private final MobileShoppingService shoppingService;

    /**
     * This test tries to insert a shopping List
     * with a single shopping Item.
     * <p>
     * Item, Category, and Brand does not exist previously
     * Store exists previously
     *
     * @throws UserNotFoundException if user id does not exist
     */
    @DisplayName("Should insert brand new shopping item from mobile")
    @Test
    public void insertBrandNewElements() throws UserNotFoundException {
        //Given
        Long shoppingListMobileId = 10L;
        Long shoppingItemMobileId = 11L;
        Long itemMobileId = 12L;
        Long categoryMobileId = 14L;
        Long brandMobileId = 15L;
        Long storeId = 1L;

        String shoppingItemName = "Mobile Shopping Item";
        String brandName = "Mobile Brand";
        String categoryName = "Mobile Category";
        String storeName = "Test Store 1";

        assertNotNull(shoppingService);
        MobileShoppingListRequest shoppingListRequest = new MobileShoppingListRequest();
        shoppingListRequest.setMobileId(shoppingListMobileId);
        List<MobileShoppingItemRequest> shoppingItemRequests = new ArrayList<>();

        MobileShoppingItemRequest shoppingItemRequest = new MobileShoppingItemRequest();
        shoppingItemRequest.setShoppingItemMobileId(shoppingItemMobileId);
        shoppingItemRequest.setItemMobileId(itemMobileId);

        MobileStoreRequest storeRequest = new MobileStoreRequest();
        storeRequest.setId(storeId);
        storeRequest.setName(storeName);

        MobileBrandRequest brandRequest = new MobileBrandRequest();
        brandRequest.setMobileId(brandMobileId);
        brandRequest.setName(brandName);

        MobileCategoryRequest categoryRequest = new MobileCategoryRequest();
        categoryRequest.setMobileId(categoryMobileId);
        categoryRequest.setName(categoryName);

        shoppingItemRequest.setBrand(brandRequest);
        shoppingItemRequest.setCategory(categoryRequest);
        shoppingItemRequest.setCurrency("MXN");
        shoppingItemRequest.setName(shoppingItemName);
        shoppingItemRequest.setQuantity(1.0);
        shoppingItemRequest.setUnit("Unit");
        shoppingItemRequest.setUnitPrice(2000);

        shoppingItemRequests.add(shoppingItemRequest);
        shoppingListRequest.setShoppingItems(shoppingItemRequests);
        shoppingListRequest.setStore(storeRequest);
        shoppingListRequest.setShoppingDate(LocalDate.now());
        shoppingListRequest.setUserId(1L);

        //when
        MobileShoppingList response = shoppingService.save(shoppingListRequest);

        //then
        verifyShoppingList(shoppingListRequest, response);
        for (MobileShoppingItem shoppingItem : response.getShoppingItems()) {
            verifyShoppingItem(shoppingItemRequest, shoppingItem);
        }
    }

    /**
     * This test tries to insert a new shopping Item
     * with an already existing item, brand, category and store
     * in persistence
     * <p>
     * User id is valid
     *
     * @throws UserNotFoundException if user id is not found
     */
    @DisplayName("Should insert new shopping item when Item, Category and Brand exists")
    @Test
    public void insertShoppingItemsAlreadyPersistedElements() throws UserNotFoundException {
        //Given
        Long shoppingListMobileId = 20L;
        Long shoppingItemMobileId = 21L;
        Long storeId = 1L;
        Long itemId = 1L;
        Long brandId = 1L;
        Long categoryId = 1L;
        String storeName = "Test Store 1";
        String itemName = "Test Item id:1 b_id:1 c_id:1";
        String brandName = "Test Brand 1";
        String categoryName = "Test Category 1";

        MobileShoppingListRequest shoppingListRequest = new MobileShoppingListRequest();
        shoppingListRequest.setMobileId(shoppingListMobileId);
        shoppingListRequest.setUserId(1L);
        shoppingListRequest.setShoppingDate(LocalDate.now());

        MobileStoreRequest storeRequest = new MobileStoreRequest();
        storeRequest.setId(storeId);
        storeRequest.setName(storeName);

        MobileShoppingItemRequest shoppingItemRequest = new MobileShoppingItemRequest();
        shoppingItemRequest.setShoppingItemMobileId(shoppingItemMobileId);
        shoppingItemRequest.setUnit("Unit");
        shoppingItemRequest.setCurrency("MXN");
        shoppingItemRequest.setUnitPrice(2500);
        shoppingItemRequest.setQuantity(1.0);

        shoppingItemRequest.setId(itemId);
        shoppingItemRequest.setName(itemName);

        MobileBrandRequest brandRequest = new MobileBrandRequest();
        brandRequest.setId(brandId);
        brandRequest.setName(brandName);

        MobileCategoryRequest categoryRequest = new MobileCategoryRequest();
        categoryRequest.setId(categoryId);
        categoryRequest.setName(categoryName);

        shoppingItemRequest.setBrand(brandRequest);
        shoppingItemRequest.setCategory(categoryRequest);

        List<MobileShoppingItemRequest> shoppingItemRequests = new ArrayList<>();
        shoppingItemRequests.add(shoppingItemRequest);

        shoppingListRequest.setStore(storeRequest);
        shoppingListRequest.setShoppingItems(shoppingItemRequests);

        //when
        MobileShoppingList response = shoppingService.save(shoppingListRequest);

        //then
        verifyShoppingList(shoppingListRequest, response);

        for (MobileShoppingItem itemResponse : response.getShoppingItems()) {
            verifyShoppingItem(shoppingItemRequest, itemResponse);
        }
    }

    /**
     * This test tries to insert two new shopping items.
     * The items have different name, different categories but same brand.
     * Brand, Items and Categories do not exist previously in DB
     * <p>
     * User id is valid
     *
     * @throws UserNotFoundException if user id does not exist
     */
    @DisplayName("Should insert two brand new shopping item with same brand from mobile")
    @Test
    public void insertTwoBrandNewElements() throws UserNotFoundException {
        //Given
        Long shoppingListMobileId = 10L;
        Long shoppingItemMobileId_A = 11L;
        Long itemMobileId_A = 12L;
        Long storeMobileId = 13L;
        Long categoryMobileId_A = 14L;
        Long brandMobileId = 15L;
        Long shoppingItemMobileId_B = 16L;
        Long itemMobileId_B = 17L;
        Long categoryMobileId_B = 18L;

        String shoppingItemName_A = "Mobile Shopping Item A";
        String brandName = "Mobile Brand";
        String categoryName_A = "Mobile Category A";
        String storeName = "Mobile Store";

        String shoppingItemName_B = "Mobile Shopping Item A";
        String categoryName_B = "Mobile Category A";

        assertNotNull(shoppingService);
        MobileShoppingListRequest shoppingListRequest = new MobileShoppingListRequest();
        shoppingListRequest.setMobileId(shoppingListMobileId);
        List<MobileShoppingItemRequest> shoppingItemRequests = new ArrayList<>();

        MobileShoppingItemRequest shoppingItemRequest_A = new MobileShoppingItemRequest();
        shoppingItemRequest_A.setShoppingItemMobileId(shoppingItemMobileId_A);
        shoppingItemRequest_A.setItemMobileId(itemMobileId_A);

        MobileStoreRequest storeRequest = new MobileStoreRequest();
        storeRequest.setMobileId(storeMobileId);
        storeRequest.setName(storeName);

        MobileBrandRequest brandRequest = new MobileBrandRequest();
        brandRequest.setMobileId(brandMobileId);
        brandRequest.setName(brandName);

        MobileCategoryRequest categoryRequest_A = new MobileCategoryRequest();
        categoryRequest_A.setMobileId(categoryMobileId_A);
        categoryRequest_A.setName(categoryName_A);

        shoppingItemRequest_A.setBrand(brandRequest);
        shoppingItemRequest_A.setCategory(categoryRequest_A);
        shoppingItemRequest_A.setCurrency("MXN");
        shoppingItemRequest_A.setName(shoppingItemName_A);
        shoppingItemRequest_A.setQuantity(1.0);
        shoppingItemRequest_A.setUnit("Unit");
        shoppingItemRequest_A.setUnitPrice(2000);

        MobileShoppingItemRequest shoppingItemRequest_B = new MobileShoppingItemRequest();
        shoppingItemRequest_B.setShoppingItemMobileId(shoppingItemMobileId_B);
        shoppingItemRequest_B.setItemMobileId(itemMobileId_B);

        MobileCategoryRequest categoryRequest_B = new MobileCategoryRequest();
        categoryRequest_B.setMobileId(categoryMobileId_B);
        categoryRequest_B.setName(categoryName_B);

        shoppingItemRequest_B.setBrand(brandRequest);
        shoppingItemRequest_B.setCategory(categoryRequest_B);
        shoppingItemRequest_B.setCurrency("MXN");
        shoppingItemRequest_B.setName(shoppingItemName_B);
        shoppingItemRequest_B.setQuantity(1.0);
        shoppingItemRequest_B.setUnit("Unit");
        shoppingItemRequest_B.setUnitPrice(2000);

        shoppingItemRequests.add(shoppingItemRequest_A);
        shoppingItemRequests.add(shoppingItemRequest_B);
        shoppingListRequest.setShoppingItems(shoppingItemRequests);
        shoppingListRequest.setStore(storeRequest);
        shoppingListRequest.setShoppingDate(LocalDate.now());
        shoppingListRequest.setUserId(1L);

        //when
        MobileShoppingList response = shoppingService.save(shoppingListRequest);

        //then
        verifyShoppingList(shoppingListRequest, response);

        assertEquals(shoppingListRequest.getShoppingItems().size(), response.getShoppingItems().size());
        List<MobileShoppingItem> shoppingItemResponses = response.getShoppingItems();

        verifyShoppingItem(shoppingItemRequest_A, shoppingItemResponses.get(0));
        verifyShoppingItem(shoppingItemRequest_B, shoppingItemResponses.get(1));
    }

    /**
     * This test tries to insert a new shopping item with no brand.
     * Category, Item and Store do not exist previously in persistence
     * <p>
     * User id is valid
     *
     * @throws UserNotFoundException if user id does not exist
     */
    @DisplayName("Should insert brand new Item with null Brand")
    @Test
    public void insertShoppingItemWithNullBrand() throws UserNotFoundException {
        //Given
        Long shoppingListMobileId = 10L;
        Long shoppingItemMobileId = 11L;
        Long itemMobileId = 12L;
        Long storeMobileId = 13L;
        Long categoryMobileId = 14L;

        String shoppingItemName = "Mobile Shopping Item";

        String categoryName = "Mobile Category";
        String storeName = "Mobile Store";

        assertNotNull(shoppingService);
        MobileShoppingListRequest shoppingListRequest = new MobileShoppingListRequest();
        shoppingListRequest.setMobileId(shoppingListMobileId);
        List<MobileShoppingItemRequest> shoppingItemRequests = new ArrayList<>();

        MobileShoppingItemRequest shoppingItemRequest = new MobileShoppingItemRequest();
        shoppingItemRequest.setShoppingItemMobileId(shoppingItemMobileId);
        shoppingItemRequest.setItemMobileId(itemMobileId);

        MobileStoreRequest storeRequest = new MobileStoreRequest();
        storeRequest.setMobileId(storeMobileId);
        storeRequest.setName(storeName);

        MobileCategoryRequest categoryRequest = new MobileCategoryRequest();
        categoryRequest.setMobileId(categoryMobileId);
        categoryRequest.setName(categoryName);

        shoppingItemRequest.setCategory(categoryRequest);
        shoppingItemRequest.setCurrency("MXN");
        shoppingItemRequest.setName(shoppingItemName);
        shoppingItemRequest.setQuantity(1.0);
        shoppingItemRequest.setUnit("Unit");
        shoppingItemRequest.setUnitPrice(2000);

        shoppingItemRequests.add(shoppingItemRequest);
        shoppingListRequest.setShoppingItems(shoppingItemRequests);
        shoppingListRequest.setStore(storeRequest);
        shoppingListRequest.setShoppingDate(LocalDate.now());
        shoppingListRequest.setUserId(1L);

        //when
        MobileShoppingList response = shoppingService.save(shoppingListRequest);

        //then
        verifyShoppingList(shoppingListRequest, response);

        for (MobileShoppingItem itemResponse : response.getShoppingItems()) {
            verifyShoppingItem(shoppingItemRequest, itemResponse);
        }
    }

    /**
     * This test tries to throw an exception when User id is either
     * not set previously in request or does not exist
     * <p>
     * {@code MobileShoppingListRequest#userId} is primitive,
     * so if it's not set previously, it will default to 0
     */
    @DisplayName("Should throw user exception when user id not found")
    @Test
    public void shouldThrowUserNotFound() {
        //Given
        Long shoppingListMobileId = 10L;
        Long shoppingItemMobileId = 11L;
        Long itemMobileId = 12L;
        Long storeMobileId = 13L;
        Long categoryMobileId = 14L;
        Long brandMobileId = 15L;

        String shoppingItemName = "Mobile Shopping Item";
        String brandName = "Mobile Brand";
        String categoryName = "Mobile Category";
        String storeName = "Mobile Store";

        assertNotNull(shoppingService);
        MobileShoppingListRequest shoppingListRequest = new MobileShoppingListRequest();
        shoppingListRequest.setMobileId(shoppingListMobileId);
        List<MobileShoppingItemRequest> shoppingItemRequests = new ArrayList<>();

        MobileShoppingItemRequest shoppingItemRequest = new MobileShoppingItemRequest();
        shoppingItemRequest.setShoppingItemMobileId(shoppingItemMobileId);
        shoppingItemRequest.setItemMobileId(itemMobileId);

        MobileStoreRequest storeRequest = new MobileStoreRequest();
        storeRequest.setMobileId(storeMobileId);
        storeRequest.setName(storeName);

        MobileBrandRequest brandRequest = new MobileBrandRequest();
        brandRequest.setMobileId(brandMobileId);
        brandRequest.setName(brandName);

        MobileCategoryRequest categoryRequest = new MobileCategoryRequest();
        categoryRequest.setMobileId(categoryMobileId);
        categoryRequest.setName(categoryName);

        shoppingItemRequest.setBrand(brandRequest);
        shoppingItemRequest.setCategory(categoryRequest);
        shoppingItemRequest.setCurrency("MXN");
        shoppingItemRequest.setName(shoppingItemName);
        shoppingItemRequest.setQuantity(1.0);
        shoppingItemRequest.setUnit("Unit");
        shoppingItemRequest.setUnitPrice(2000);

        shoppingItemRequests.add(shoppingItemRequest);
        shoppingListRequest.setShoppingItems(shoppingItemRequests);
        shoppingListRequest.setStore(storeRequest);
        shoppingListRequest.setShoppingDate(LocalDate.now());

        //when
        Exception exception = assertThrows(UserNotFoundException.class,
                () -> shoppingService.save(shoppingListRequest));

        assertEquals(String.format("User with id %d not found", 0),
                exception.getMessage());
    }

    /**
     * This test tries to throw a NPE if parameter is null reference.
     * Fail-fast strategy
     *
     * @throws UserNotFoundException if user id is not valid
     */
    @DisplayName("Should throw NPE if request is null")
    @Test
    public void insertingShouldFailIfRequestIstNull() throws UserNotFoundException {
        //when
        assertThrows(NullPointerException.class,
                () -> shoppingService.save(null));
    }

    private void verifyShoppingList(MobileShoppingListRequest request, MobileShoppingList response) {
        assertNotNull(response);
        assertEquals(request.getMobileId(), response.getMobileId());
        assertTrue(response.getRemoteId() > 0);

        verifyStore(request.getStore(), response.getStore());

        assertNotNull(response.getShoppingItems());
        assertFalse(response.getShoppingItems().isEmpty());
    }

    private void verifyShoppingItem(MobileShoppingItemRequest shoppingItemRequest,
                                    MobileShoppingItem shoppingItemResponse) {

        String name = shoppingItemRequest.getName();
        Long shoppingItemMobileId = shoppingItemRequest.getShoppingItemMobileId();
        Long itemMobileId = shoppingItemRequest.getItemMobileId();
        String currency = shoppingItemRequest.getCurrency();
        int unitPrice = shoppingItemRequest.getUnitPrice();
        String unit = shoppingItemRequest.getUnit();
        double quantity = shoppingItemRequest.getQuantity();

        assertNotNull(shoppingItemResponse);
        assertNotNull(shoppingItemResponse.getItem());
        MobileItem itemResponse = shoppingItemResponse.getItem();

        assertEquals(shoppingItemMobileId, shoppingItemResponse.getMobileId());
        assertTrue(shoppingItemResponse.getRemoteId() > 0);
        assertEquals(currency, shoppingItemResponse.getCurrency());
        assertEquals(unit, shoppingItemResponse.getUnit());
        assertEquals(quantity, shoppingItemResponse.getQuantity());
        assertEquals(unitPrice, shoppingItemResponse.getUnitPrice());

        assertEquals(itemMobileId, itemResponse.getMobileId());
        assertTrue(itemResponse.getRemoteId() > 0);
        assertEquals(name, itemResponse.getName());

        if (shoppingItemRequest.getBrand() == null) {
            assertNull(itemResponse.getBrand());
        } else {
            verifyBrand(shoppingItemRequest.getBrand(), itemResponse.getBrand());
        }

        verifyCategory(shoppingItemRequest.getCategory(), itemResponse.getCategory());
    }

    private void verifyBrand(MobileBrandRequest request, @NonNull MobileBrand response) {
        Long mobileId = request.getMobileId();
        String name = request.getName();

        assertNotNull(response);
        assertEquals(mobileId, response.getMobileId());
        assertEquals(name, response.getName());
        assertTrue(response.getRemoteId() > 0);
    }

    private void verifyCategory(MobileCategoryRequest request, @NonNull MobileCategory response) {
        Long mobileId = request.getMobileId();
        String name = request.getName();

        assertNotNull(response);
        assertEquals(mobileId, response.getMobileId());
        assertEquals(name, response.getName());
        assertTrue(response.getRemoteId() > 0);
    }

    private void verifyStore(MobileStoreRequest request, @NonNull MobileStore response) {
        Long mobileId = request.getMobileId();
        String name = request.getName();

        assertNotNull(response);
        assertEquals(mobileId, response.getMobileId());
        assertEquals(name, response.getName());
        assertTrue(response.getRemoteId() > 0);
    }
}
