package mx.kinich49.itemtracker.validators.impl;

import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.validators.MainShoppingListValidationCondition;
import mx.kinich49.itemtracker.utils.CollectionsUtil;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingListRequestValidationConditionImpl implements MainShoppingListValidationCondition {

    @Override
    public Optional<String> validate(MainShoppingListRequest shoppingList) {
        if (shoppingList == null)
            return Optional.of("Shopping List must not be null");

        if (shoppingList.getShoppingDate() == null)
            return Optional.of("Shopping list must have a date set");

        if (CollectionsUtil.isNullOrEmpty(shoppingList.getShoppingItems())) {
            return Optional.of("Shopping List must have at least one shopping item");
        }

        return Optional.empty();
    }
}
