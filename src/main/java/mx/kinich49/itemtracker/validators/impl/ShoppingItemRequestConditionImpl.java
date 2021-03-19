package mx.kinich49.itemtracker.validators.impl;

import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.validators.Condition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingItemRequestConditionImpl implements Condition<MainShoppingItemRequest> {

    @Override
    public Optional<String> assertCondition(MainShoppingItemRequest shoppingItemRequests) {
        return Optional.empty();
    }
}
