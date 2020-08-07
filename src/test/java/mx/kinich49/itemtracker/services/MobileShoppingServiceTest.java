package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.mobile.responses.*;
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
public class MobileShoppingServiceTest {

    @Autowired
    MobileShoppingService shoppingService;

    @DisplayName("Should insert brand new shopping item from mobile")
    @Test
    public void test() throws UserNotFoundException {
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
        shoppingListRequest.setUserId(1L);

        //when
        MobileShoppingListResponse response = shoppingService.save(shoppingListRequest);

        //then
        assertNotNull(response);
        assertEquals(shoppingListMobileId, response.getMobileId());
        assertTrue(response.getId() > 0);

        verifyStore(storeRequest, response.getStore());

        assertNotNull(response.getShoppingItems());
        assertFalse(response.getShoppingItems().isEmpty());

        for (MobileShoppingItemResponse shoppingItem : response.getShoppingItems()) {
            verifyShoppingItem(shoppingItemRequest, shoppingItem, shoppingItem.getItem());
        }
    }


    private void verifyShoppingItem(MobileShoppingItemRequest shoppingItemRequest,
                                    MobileShoppingItemResponse shoppingItemResponse,
                                    MobileItemResponse itemResponse) {

        String name = shoppingItemRequest.getName();
        Long shoppingItemMobileId = shoppingItemRequest.getShoppingItemMobileId();
        Long itemMobileId = shoppingItemRequest.getItemMobileId();
        String currency = shoppingItemRequest.getCurrency();
        int unitPrice = shoppingItemRequest.getUnitPrice();
        String unit = shoppingItemRequest.getUnit();
        double quantity = shoppingItemRequest.getQuantity();

        assertNotNull(shoppingItemResponse);
        assertNotNull(itemResponse);

        assertEquals(shoppingItemMobileId, shoppingItemResponse.getMobileId());
        assertTrue(shoppingItemResponse.getId() > 0);
        assertEquals(currency, shoppingItemResponse.getCurrency());
        assertEquals(unit, shoppingItemResponse.getUnit());
        assertEquals(quantity, shoppingItemResponse.getQuantity());
        assertEquals(unitPrice, shoppingItemResponse.getUnitPrice());

        assertEquals(itemMobileId, itemResponse.getMobileId());
        assertTrue(itemResponse.getId() > 0);
        assertEquals(name, itemResponse.getName());

        if (shoppingItemRequest.getBrand() == null) {
            assertNull(itemResponse.getBrand());
        } else {
            verifyBrand(shoppingItemRequest.getBrand(), itemResponse.getBrand());
        }

        verifyCategory(shoppingItemRequest.getCategory(), itemResponse.getCategory());
    }

    private void verifyBrand(MobileBrandRequest request, @NonNull MobileBrandResponse response) {
        Long mobileId = request.getMobileId();
        String name = request.getName();

        assertNotNull(response);
        assertEquals(mobileId, response.getMobileId());
        assertEquals(name, response.getName());
        assertTrue(response.getId() > 0);
    }

    private void verifyCategory(MobileCategoryRequest request, @NonNull MobileCategoryResponse response) {
        Long mobileId = request.getMobileId();
        String name = request.getName();

        assertNotNull(response);
        assertEquals(mobileId, response.getMobileId());
        assertEquals(name, response.getName());
        assertTrue(response.getId() > 0);
    }

    private void verifyStore(MobileStoreRequest request, @NonNull MobileStoreResponse response) {
        Long mobileId = request.getMobileId();
        String name = request.getName();

        assertNotNull(response);
        assertEquals(mobileId, response.getMobileId());
        assertEquals(name, response.getName());
        assertTrue(response.getId() > 0);
    }
}
