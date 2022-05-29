package mx.kinich49.itemtracker.validators.shoppingitem;

import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemUnitConditionTest {

    ItemUnitCondition subject;

    @BeforeEach
    void setUp() {
        subject = new ItemUnitCondition();
    }

    @Test
    @DisplayName("Should return error when unit is missing")
    void shouldReturnError_whenUnitIsMissing() throws ValidationFlowException {
        //given
        var request = new MainShoppingItemRequest();
        var param = new ShoppingItemRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isPresent());
        assertEquals(ErrorConstants.MISSING_ITEM_UNIT, result.get().getCode());
    }

    @Test
    @DisplayName("Should return empty when unit is present")
    void shouldReturnEmpty_whenUnitIsPresent() throws ValidationFlowException {
        //given
        var request = new MainShoppingItemRequest();
        request.setUnit("UNIT");
        var param = new ShoppingItemRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isEmpty());
    }
}
