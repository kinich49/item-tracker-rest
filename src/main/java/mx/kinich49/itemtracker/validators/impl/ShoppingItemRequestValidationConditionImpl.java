package mx.kinich49.itemtracker.validators.impl;

import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.validators.ShoppingItemRequestValidationCondition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingItemRequestValidationConditionImpl implements ShoppingItemRequestValidationCondition {

    @Override
    public Optional<String> validate(MainShoppingItemRequest shoppingItemRequests) {
        return Optional.empty();
    }
}
