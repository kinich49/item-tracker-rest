package mx.kinich49.itemtracker.validators.shoppingitem;

import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemNameConditionTest {

    ItemNameCondition subject;

    @BeforeEach
    void setUp() {
        subject = new ItemNameCondition();
    }

    @Test
    @DisplayName("Should return error when name is missing")
    void shouldReturnError_whenNameIsMissing() throws ValidationFlowException {
        //given
        var request = new MainShoppingItemRequest();
        var param = new ShoppingItemRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isPresent());
        assertEquals(ErrorConstants.MISSING_ITEM_NAME, result.get().getCode());
    }

    @Test
    @DisplayName("Should return empty when name is present")
    void shouldReturnEmpty_whenNameIsPresent() throws ValidationFlowException {
        //given
        var request = new MainShoppingItemRequest();
        request.setName("Test item");
        var param = new ShoppingItemRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isEmpty());
    }
}
