package mx.kinich49.itemtracker.validators;

import mx.kinich49.itemtracker.requests.main.CategoryRequest;
import mx.kinich49.itemtracker.validators.impl.CategoryRequestConditionImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CategoryRequestConditionTest {

    @InjectMocks
    CategoryRequestConditionImpl subject;

    @Test
    @DisplayName("Should return no message when category request has only name")
    void shouldReturn_empty_when_categoryRequestHasName() {
        //given
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Dummy Brand name");

        //when
        Optional<String> result = subject.assertCondition(categoryRequest);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return no message when category has only ID")
    void shouldReturn_empty_when_categoryRequestHasID() {
        //given
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setId(1L);

        //when
        Optional<String> result = subject.assertCondition(categoryRequest);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return error message when category request is null")
    void shouldReturn_errorMessage_when_CategoryRequestIsNull() {
        //when
        Optional<String> result = subject.assertCondition(null);

        //then
        assertTrue(result.isPresent());
        String errorMessage = result.get();
        assertTrue(errorMessage.contains("Category must not be null."));
    }

    /**
     * A valid category must contain either/both a name and/or an ID
     */
    @Test
    @DisplayName("Should return error message when category request has no name and  no ID")
    void shouldReturn_errorMessage_when_categoryHasNoNameAndNoID() {
        //given
        CategoryRequest categoryRequest = new CategoryRequest();

        //when
        Optional<String> result = subject.assertCondition(categoryRequest);

        //then
        assertTrue(result.isPresent());
        String errorMessage = result.get();
        assertTrue(errorMessage.contains("Category must have a Name or ID."));
    }

    @Test
    @DisplayName("Should return error message when category request has name and ID")
    void shouldReturn_empty_when_categoryHasNameAndID() {
        //given
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Dummy category name");
        categoryRequest.setId(1L);

        //when
        Optional<String> result = subject.assertCondition(categoryRequest);

        //then
        assertTrue(result.isEmpty());
    }
}
