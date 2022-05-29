package mx.kinich49.itemtracker.validators.storerequest;

import mx.kinich49.itemtracker.requests.main.StoreRequest;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreNameIdConditionTest {

    StoreNameIdCondition subject;

    @BeforeEach
    void setUp() {
        subject = new StoreNameIdCondition();
    }

    @Test
    @DisplayName("Should return no message when store request has name")
    void shouldReturn_empty_when_storeRequestHasName()  throws ValidationFlowException {
        //given
        var request = new StoreRequest();
        request.setName("Test Brand");
        var param = new StoreRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return no message when store request has ID")
    void shouldReturn_empty_when_brandRequestHasID() throws ValidationFlowException{
        //given
        var request = new StoreRequest();
        request.setId(1L);
        var param = new StoreRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isEmpty());
    }

//    /**
//     * An {@link mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest}
//     * without brand is valid scenario (Brand request is null)
//     */
//    @Test
//    @DisplayName("Should return no message when brand request is null")
//    void shouldReturn_empty_when_BrandRequestIsNull() {
//        //when
//        Optional<String> result = subject.assertCondition(new BrandRequestParameter(null));
//
//        //then
//        assertTrue(result.isEmpty());
//    }

    @Test
    @DisplayName("Should return error message when store has no name and no ID")
    void shouldReturn_errorMessage_when_storeHasNoNameAndNoId() throws ValidationFlowException {
        //given
        var param = new StoreRequestParameter(new StoreRequest());

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isPresent());
        assertEquals(ErrorConstants.REQUEST_NAME_IS_NULL_AND_ID_IS_NULL, result.get().getCode());
    }
}
