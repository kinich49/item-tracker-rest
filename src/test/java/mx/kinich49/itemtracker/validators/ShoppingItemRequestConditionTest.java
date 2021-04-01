package mx.kinich49.itemtracker.validators;

import mx.kinich49.itemtracker.requests.main.BrandRequest;
import mx.kinich49.itemtracker.requests.main.CategoryRequest;
import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.validators.impl.BrandRequestConditionImpl;
import mx.kinich49.itemtracker.validators.impl.CategoryRequestConditionImpl;
import mx.kinich49.itemtracker.validators.impl.ShoppingItemRequestConditionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingItemRequestConditionTest {

    private ShoppingItemRequestConditionImpl subject;
    private BrandRequestConditionImpl brandRequestCondition;
    private CategoryRequestConditionImpl categoryRequestCondition;

    @BeforeEach
    public void setUp() {
        brandRequestCondition = new BrandRequestConditionImpl();
        categoryRequestCondition = new CategoryRequestConditionImpl();
        subject = new ShoppingItemRequestConditionImpl(brandRequestCondition, categoryRequestCondition);
    }

    @Test
    @DisplayName("Should return no error when item has all properties")
    public void shouldReturn_empty_when_itemHasAllProperties() {
        //given
        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();
        itemRequest.setQuantity(1.0);
        itemRequest.setUnitPrice(100);
        itemRequest.setCurrency("MXN");
        itemRequest.setName("Dummy item name");
        itemRequest.setUnit("Unit");

        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Dummy brand");

        CategoryRequest category = new CategoryRequest();
        category.setName("Dummy Category");
        itemRequest.setCategory(category);

        //when
        Optional<String> result = subject.assertCondition(itemRequest);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return error message when item has no name")
    public void shouldReturnMessage_when_itemHasNoName() {
        //given
        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();
        itemRequest.setQuantity(1.0);
        itemRequest.setUnitPrice(100);
        itemRequest.setCurrency("MXN");
        itemRequest.setUnit("Unit");

        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Dummy brand");
        itemRequest.setBrand(brandRequest);

        CategoryRequest category = new CategoryRequest();
        category.setName("Dummy Category");
        itemRequest.setCategory(category);

        //when
        Optional<String> result = subject.assertCondition(itemRequest);

        //then
        assertTrue(result.isPresent());
        String message = result.get();
        assertFalse(message.isEmpty());
        assertTrue(message.contains("Item is missing Name property."));
    }

    @Test
    @DisplayName("Should return error message when item has no unit")
    public void shouldReturn_message_when_itemHasNoUnit(){
        //given
        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();
        itemRequest.setQuantity(1.0);
        itemRequest.setUnitPrice(100);
        itemRequest.setCurrency("MXN");
        itemRequest.setName("Dummy Name");

        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Dummy brand");
        itemRequest.setBrand(brandRequest);

        CategoryRequest category = new CategoryRequest();
        category.setName("Dummy Category");
        itemRequest.setCategory(category);

        //when
        Optional<String> result = subject.assertCondition(itemRequest);

        //then
        assertTrue(result.isPresent());
        String message = result.get();
        assertFalse(message.isEmpty());
        assertTrue(message.contains("Item is missing Unit property."));
    }

    @Test
    @DisplayName("Should return error message when item has no currency")
    public void shouldReturn_message_when_itemHasNoCurrency(){
        //given
        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();
        itemRequest.setQuantity(1.0);
        itemRequest.setUnitPrice(100);
        itemRequest.setName("Dummy name");
        itemRequest.setUnit("Unit");

        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Dummy brand");
        itemRequest.setBrand(brandRequest);

        CategoryRequest category = new CategoryRequest();
        category.setName("Dummy Category");
        itemRequest.setCategory(category);

        //when
        Optional<String> result = subject.assertCondition(itemRequest);

        //then
        assertTrue(result.isPresent());
        String message = result.get();
        assertFalse(message.isEmpty());
        assertTrue(message.contains("Item is missing Currency property."));
    }

    @Test
    @DisplayName("Should return error message when item has no unit price")
    public void shouldReturnMessage_when_itemHasNoUnitPrice() {
        //given
        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();
        itemRequest.setQuantity(1.0);
        itemRequest.setCurrency("MXN");
        itemRequest.setName("Dummy item name");
        itemRequest.setUnit("Unit");

        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Dummy brand");

        CategoryRequest category = new CategoryRequest();
        category.setName("Dummy Category");
        itemRequest.setCategory(category);

        //when
        Optional<String> result = subject.assertCondition(itemRequest);

        //then
        assertTrue(result.isPresent());
        String message = result.get();
        assertFalse(message.isEmpty());
        assertTrue(message.contains("Item is missing Unit Price property."));
    }

    @Test
    @DisplayName("Should return error message when item has no quantity")
    public void shouldReturnMessage_when_itemHasNoQuantity() {
        //given
        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();
        itemRequest.setUnitPrice(100);
        itemRequest.setCurrency("MXN");
        itemRequest.setName("Dummy item name");
        itemRequest.setUnit("Unit");

        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Dummy brand");

        CategoryRequest category = new CategoryRequest();
        category.setName("Dummy Category");
        itemRequest.setCategory(category);

        //when
        Optional<String> result = subject.assertCondition(itemRequest);

        //then
        assertTrue(result.isPresent());
        String message = result.get();
        assertFalse(message.isEmpty());
        assertTrue(message.contains("Item is missing Quantity property."));
    }

    @Test
    @DisplayName("Should return error message when item has no category")
    public void shouldReturnMessage_when_itemHasNoCategory() {
        //given
        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();
        itemRequest.setQuantity(1.0);
        itemRequest.setUnitPrice(100);
        itemRequest.setCurrency("MXN");
        itemRequest.setName("Dummy item name");
        itemRequest.setUnit("Unit");

        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Dummy brand");

        //when
        Optional<String> result = subject.assertCondition(itemRequest);

        //then
        assertTrue(result.isPresent());
        String message = result.get();
        assertFalse(message.isEmpty());
        assertTrue(message.contains("Category must not be null."));
    }

    @Test
    @DisplayName("Should return error message when item has no properties")
    public void shouldReturnMessage_when_itemHasNoProperties() {
        //given
        MainShoppingItemRequest itemRequest = new MainShoppingItemRequest();

        //when
        Optional<String> result = subject.assertCondition(itemRequest);

        //then
        assertTrue(result.isPresent());
        String message = result.get();
        assertFalse(message.isEmpty());

        assertTrue(message.contains("Item is missing Quantity property."));
        assertTrue(message.contains("Item is missing Unit Price property."));
        assertTrue(message.contains("Item is missing Currency property."));
        assertTrue(message.contains("Item is missing Unit property."));
        assertTrue(message.contains("Item is missing Name property."));
        assertTrue(message.contains("Category must not be null."));
    }
}
