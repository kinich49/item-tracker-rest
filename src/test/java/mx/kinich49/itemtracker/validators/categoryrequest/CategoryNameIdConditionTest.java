package mx.kinich49.itemtracker.validators.categoryrequest;

import mx.kinich49.itemtracker.requests.main.BrandRequest;
import mx.kinich49.itemtracker.requests.main.CategoryRequest;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import mx.kinich49.itemtracker.validators.brandrequest.BrandNameIdCondition;
import mx.kinich49.itemtracker.validators.brandrequest.BrandRequestParameter;
import mx.kinich49.itemtracker.validators.categoryRequest.CategoryNameIdCondition;
import mx.kinich49.itemtracker.validators.categoryRequest.CategoryRequestParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryNameIdConditionTest {

    CategoryNameIdCondition subject;


    @BeforeEach
    void setUp() {
        subject = new CategoryNameIdCondition();
    }

    @Test
    @DisplayName("Should return no message when category request has name")
    void shouldReturn_empty_when_brandRequestHasName()  throws ValidationFlowException {
        //given
        var request = new CategoryRequest();
        request.setName("Test Category");
        var param = new CategoryRequestParameter(request);

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return no message when category request has ID")
    void shouldReturn_empty_when_brandRequestHasID() throws ValidationFlowException{
        //given
        var request = new CategoryRequest();
        request.setId(1L);
        var param = new CategoryRequestParameter(request);

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
    @DisplayName("Should return error message when category has no name and no ID")
    void shouldReturn_errorMessage_when_brandHasNoNameAndNoId() throws ValidationFlowException {
        //given
        var param = new CategoryRequestParameter(new CategoryRequest());

        //when
        var result = subject.assertCondition(param);

        //then
        assertTrue(result.isPresent());
        assertEquals(ErrorConstants.REQUEST_NAME_IS_NULL_AND_ID_IS_NULL, result.get().getCode());
    }
}
