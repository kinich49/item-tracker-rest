package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.services.MainShoppingRequestValidator;
import mx.kinich49.itemtracker.validators.MainShoppingListValidationCondition;
import mx.kinich49.itemtracker.validators.ShoppingItemRequestValidationCondition;
import mx.kinich49.itemtracker.validators.StoreRequestValidationCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MainShoppingRequestValidatorImpl implements MainShoppingRequestValidator {

    private final ShoppingItemRequestValidationCondition itemRequestCondition;
    private final MainShoppingListValidationCondition shoppingListCondition;
    private final StoreRequestValidationCondition storeRequestValidationCondition;

    @Autowired
    public MainShoppingRequestValidatorImpl(ShoppingItemRequestValidationCondition itemRequestCondition,
                                            MainShoppingListValidationCondition shoppingListCondition,
                                            StoreRequestValidationCondition storeRequestValidationCondition) {
        this.itemRequestCondition = itemRequestCondition;
        this.shoppingListCondition = shoppingListCondition;
        this.storeRequestValidationCondition = storeRequestValidationCondition;
    }

    @Override
    public void validate(MainShoppingListRequest request) throws BusinessException {
        var optionalCondition = shoppingListCondition.validate(request)
                .or(() -> storeRequestValidationCondition.validate(request.getStore()))
                .or(() -> validateShoppingItemList(request.getShoppingItems()));

        if (optionalCondition.isPresent())
            throw new BusinessException(optionalCondition.get());
    }

    private Optional<String> validateShoppingItemList(List<MainShoppingItemRequest> itemRequests) {
        for (MainShoppingItemRequest itemRequest : itemRequests) {
            var condition = itemRequestCondition.validate(itemRequest);
            if (condition.isPresent())
                return condition;
        }

        return Optional.empty();
    }
}
