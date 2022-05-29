package mx.kinich49.itemtracker.validators.shoppingitem;

import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemQuantityConditionTest {

    ItemQuantityCondition subject;

    @BeforeEach
    void setUp() {
        subject = new ItemQuantityCondition();
    }

    @Test
    @DisplayName("Should return error when quantity is missing")
    void shouldReturnError_whenNameIsMissing() throws ValidationFlowException {
        //given
        var request = new MainShoppingItemRequest();
        var param = new ShoppingItemRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isPresent());
        assertEquals(ErrorConstants.MISSING_ITEM_QUANTITY, result.get().getCode());
    }

    @Test
    @DisplayName("Should return empty when quantity is present")
    void shouldReturnEmpty_whenQuantityIsPresent() throws ValidationFlowException {
        //given
        var request = new MainShoppingItemRequest();
        request.setQuantity(1.0);
        var param = new ShoppingItemRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isEmpty());
    }
}
