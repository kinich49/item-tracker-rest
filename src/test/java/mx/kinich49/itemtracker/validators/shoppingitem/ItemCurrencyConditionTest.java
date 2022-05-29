package mx.kinich49.itemtracker.validators.shoppingitem;

import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemCurrencyConditionTest {

    ItemCurrencyCondition subject;

    @BeforeEach
    void setUp() {
        subject = new ItemCurrencyCondition();
    }

    @Test
    @DisplayName("Should return error when currency is missing")
    void shouldReturnError_whenCurrencyIsMissing() throws ValidationFlowException {
        //given
        var request = new MainShoppingItemRequest();
        var param = new ShoppingItemRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isPresent());
        assertEquals(ErrorConstants.MISSING_ITEM_CURRENCY, result.get().getCode());
    }

    @Test
    @DisplayName("Should return empty when currency is present")
    void shouldReturnEmpty_whenCurrencyIsPresent() throws ValidationFlowException {
        //given
        var request = new MainShoppingItemRequest();
        request.setCurrency("USD");
        var param = new ShoppingItemRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isEmpty());
    }
}
