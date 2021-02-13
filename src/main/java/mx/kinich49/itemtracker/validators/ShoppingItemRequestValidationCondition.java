package mx.kinich49.itemtracker.validators;

import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;

import java.util.Optional;

public interface ShoppingItemRequestValidationCondition {

    Optional<String> validate(MainShoppingItemRequest request);
}
