package mx.kinich49.itemtracker.validators.brandrequest;

import mx.kinich49.itemtracker.requests.main.BrandRequest;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BrandNameIdConditionTest {

    BrandNameIdCondition subject;


    @BeforeEach
    void setUp() {
        subject = new BrandNameIdCondition();
    }

    @Test
    @DisplayName("Should return no message when brand request has name")
    void shouldReturn_empty_when_brandRequestHasName()  throws ValidationFlowException {
        //given
        var request = new BrandRequest();
        request.setName("Test Brand");
        var param = new BrandRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return no message when brand request has ID")
    void shouldReturn_empty_when_brandRequestHasID() throws ValidationFlowException{
        //given
        var request = new BrandRequest();
        request.setId(1L);
        var param = new BrandRequestParameter(request);

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
    @DisplayName("Should return error message when brand has no name and no ID")
    void shouldReturn_errorMessage_when_brandHasNoNameAndNoId() throws ValidationFlowException {
        //given
        var param = new BrandRequestParameter(new BrandRequest());

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isPresent());
        assertEquals(ErrorConstants.REQUEST_NAME_IS_NULL_AND_ID_IS_NULL, result.get().getCode());
    }
}
