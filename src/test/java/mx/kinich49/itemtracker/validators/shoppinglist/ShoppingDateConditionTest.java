package mx.kinich49.itemtracker.validators.shoppinglist;

import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingDateConditionTest {

    ShoppingDateCondition subject;

    @BeforeEach
    void setUp() {
        subject = new ShoppingDateCondition();
    }

    @Test
    @DisplayName("Should return error when shopping date is missing")
    void shouldReturnError_whenShoppingDateIsMissing() throws ValidationFlowException {
        //given
        var request = new MainShoppingListRequest();
        var param = new ShoppingListParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isPresent());
        assertEquals(ErrorConstants.MISSING_SHOPPING_DATE, result.get().getCode());
    }

    @Test
    @DisplayName("Should return empty when shopping date is present")
    void shouldReturnEmpty_whenUnitIsPresent() throws ValidationFlowException {
        //given
        var request = new MainShoppingListRequest();
        request.setShoppingDate(LocalDate.of(2022, Month.JANUARY, 30));
        var param = new ShoppingListParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isEmpty());
    }
}
