package mx.kinich49.itemtracker.validators;

import mx.kinich49.itemtracker.requests.main.BrandRequest;
import mx.kinich49.itemtracker.requests.main.CategoryRequest;
import mx.kinich49.itemtracker.validators.impl.BrandRequestConditionImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BrandRequestConditionTest {

    @InjectMocks
    BrandRequestConditionImpl subject;

    @Test
    @DisplayName("Should return no message when brand request has name")
    void shouldReturn_empty_when_brandRequestHasName() {
        //given
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Dummy Brand name");

        //when
        Optional<String> result = subject.assertCondition(brandRequest);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return no message when brand request has ID")
    void shouldReturn_empty_when_brandRequestHasID() {
        //given
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setId(1L);

        //when
        Optional<String> result = subject.assertCondition(brandRequest);

        //then
        assertTrue(result.isEmpty());
    }

    /**
     * An {@link mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest}
     * without brand is valid scenario (Brand request is null)
     */
    @Test
    @DisplayName("Should return no message when brand request is null")
    void shouldReturn_empty_when_BrandRequestIsNull() {
        //when
        Optional<String> result = subject.assertCondition(null);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return error message when brand has no name and no ID")
    void shouldReturn_errorMessage_when_brandHasNoNameAndNoId() {
        //given
        BrandRequest brandRequest = new BrandRequest();

        //when
        Optional<String> result = subject.assertCondition(brandRequest);

        //then
        assertTrue(result.isPresent());
        String errorMessage = result.get();
        assertTrue(errorMessage.contains("Brand must have a name or a valid ID."));
    }

    @Test
    @DisplayName("Should return error message when category request has name and ID")
    void shouldReturn_empty_when_categoryHasNameAndID() {
        //given
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Dummy brand name");
        brandRequest.setId(1L);

        //when
        Optional<String> result = subject.assertCondition(brandRequest);

        //then
        assertTrue(result.isEmpty());
    }
}
