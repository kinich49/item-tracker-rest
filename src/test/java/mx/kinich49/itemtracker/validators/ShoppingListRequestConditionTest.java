package mx.kinich49.itemtracker.validators;

import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.requests.main.StoreRequest;
import mx.kinich49.itemtracker.validators.impl.ShoppingListRequestConditionImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ShoppingListRequestConditionTest {

    @InjectMocks
    ShoppingListRequestConditionImpl subject;

    @Test
    @DisplayName("Should return empty message")
    public void should_return_emptyMessage() {
        //Given
        MainShoppingListRequest request = new MainShoppingListRequest();
        request.setShoppingDate(LocalDate.now());

        List<MainShoppingItemRequest> shoppingItems = new ArrayList<>();
        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();
        shoppingItems.add(itemRequest);
        request.setShoppingItems(shoppingItems);

        //When
        Optional<String> result = subject.assertCondition(request);

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return message when request has no date")
    public void should_return_message_when_requestHasNoDate() {
        //Given
        MainShoppingListRequest request = new MainShoppingListRequest();

        List<MainShoppingItemRequest> shoppingItems = new ArrayList<>();
        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();
        shoppingItems.add(itemRequest);
        request.setShoppingItems(shoppingItems);

        StoreRequest storeRequest = new StoreRequest();
        request.setStore(storeRequest);

        //When
        Optional<String> result = subject.assertCondition(request);

        //Then
        assertTrue(result.isPresent());

        String message = result.get();
        assertFalse(message.isEmpty());
        assertFalse(message.isBlank());
    }

    @Test
    @DisplayName("Should return message when request has no shoppingItems")
    public void should_return_message_when_requestHasNoStore() {
        //Given
        MainShoppingListRequest request = new MainShoppingListRequest();
        request.setShoppingDate(LocalDate.now());

        //When
        Optional<String> result = subject.assertCondition(request);

        //Then
        assertTrue(result.isPresent());

        String message = result.get();
        assertFalse(message.isEmpty());
        assertFalse(message.isBlank());
    }

    @Test
    @DisplayName("Should return message when request has empty shopping items")
    public void should_return_message_when_requestHasEmptyShoppingItems() {
        //Given
        MainShoppingListRequest request = new MainShoppingListRequest();
        request.setShoppingDate(LocalDate.now());
        request.setShoppingItems(Collections.emptyList());

        //When
        Optional<String> result = subject.assertCondition(request);

        //Then
        assertTrue(result.isPresent());

        String message = result.get();
        assertFalse(message.isEmpty());
        assertFalse(message.isBlank());
    }


}
