package mx.kinich49.itemtracker.validators;

import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;

import java.util.Optional;

public interface MainShoppingListValidationCondition {

    Optional<String> validate(MainShoppingListRequest request);
}
