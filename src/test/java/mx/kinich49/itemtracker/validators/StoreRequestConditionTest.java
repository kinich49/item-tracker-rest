package mx.kinich49.itemtracker.validators;

import mx.kinich49.itemtracker.requests.main.StoreRequest;
import mx.kinich49.itemtracker.validators.impl.StoreRequestConditionImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StoreRequestConditionTest {


    @InjectMocks
    StoreRequestConditionImpl subject;

    @Test
    void name() {
        assertNotNull(subject);
    }

    @Test
    @DisplayName("Should return message when Request is null")
    public void should_returnMessage_when_requestIsNull() {
        //When
        Optional<String> result = subject.assertCondition(null);

        //Then
        assertTrue(result.isPresent());
        String message = result.get();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertFalse(message.isBlank());
    }

    @Test
    @DisplayName("Should return message when request has neither ID nor name")
    public void should_returnMessage_when_requestHasNoIdOrName() {
        //Given
        StoreRequest storeRequest = new StoreRequest();

        //When
        Optional<String> result = subject.assertCondition(storeRequest);

        //Then
        assertTrue(result.isPresent());
        String message = result.get();
        assertNotNull(message);
        assertFalse(message.isEmpty());
        assertFalse(message.isBlank());
    }

    @Test
    @DisplayName("Should return empty when request has ID and name")
    public void should_returnEmpty_when_RequestHasNameOrId() {
        //Given
        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setName("Test name");
        storeRequest.setId(1L);
        //When
        Optional<String> result = subject.assertCondition(storeRequest);

        //Then
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should return empty when request has name")
    public void should_returnEmpty_when_RequestHasName() {
        //Given
        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setName("Test name");
        //When
        Optional<String> result = subject.assertCondition(storeRequest);

        //Then
        assertFalse(result.isPresent());
    }


    @Test
    @DisplayName("Should return empty when request has ID")
    public void should_returnEmpty_when_RequestHasID() {
        //Given
        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setId(1L);
        //When
        Optional<String> result = subject.assertCondition(storeRequest);

        //Then
        assertFalse(result.isPresent());
    }
}
