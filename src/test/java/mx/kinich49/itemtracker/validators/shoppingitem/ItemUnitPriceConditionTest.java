package mx.kinich49.itemtracker.validators.shoppingitem;

import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemUnitPriceConditionTest {

    ItemUnitPriceCondition subject;

    @BeforeEach
    void setUp() {
        subject = new ItemUnitPriceCondition();
    }

    @Test
    @DisplayName("Should return unit price when name is missing")
    void shouldReturnError_whenUnitPriceIsMissing() throws ValidationFlowException {
        //given
        var request = new MainShoppingItemRequest();
        var param = new ShoppingItemRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isPresent());
        assertEquals(ErrorConstants.MISSING_ITEM_UNIT_PRICE, result.get().getCode());
    }

    @Test
    @DisplayName("Should return empty when name is present")
    void shouldReturnEmpty_whenUnitPriceIsPresent() throws ValidationFlowException {
        //given
        var request = new MainShoppingItemRequest();
        request.setUnitPrice(1000);
        var param = new ShoppingItemRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isEmpty());
    }
}
