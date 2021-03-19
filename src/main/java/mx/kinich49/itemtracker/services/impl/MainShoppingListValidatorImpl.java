package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.services.Validator;
import mx.kinich49.itemtracker.validators.impl.ShoppingItemRequestConditionImpl;
import mx.kinich49.itemtracker.validators.impl.ShoppingListRequestConditionImpl;
import mx.kinich49.itemtracker.validators.impl.StoreRequestConditionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MainShoppingListValidatorImpl implements Validator<MainShoppingListRequest> {

    private final ShoppingListRequestConditionImpl shoppingListCondition;
    private final ShoppingItemRequestConditionImpl itemRequestCondition;
    private final StoreRequestConditionImpl storeRequestCondition;

    @Autowired
    public MainShoppingListValidatorImpl(ShoppingListRequestConditionImpl shoppingListCondition,
                                         ShoppingItemRequestConditionImpl itemRequestCondition,
                                         StoreRequestConditionImpl storeRequestCondition) {
        this.itemRequestCondition = itemRequestCondition;
        this.shoppingListCondition = shoppingListCondition;
        this.storeRequestCondition = storeRequestCondition;
    }

    @Override
    public void validate(MainShoppingListRequest request) throws BusinessException {
        if (request == null)
            throw new BusinessException("Shopping List request must not be null");

        var errors = new ArrayList<String>();
        shoppingListCondition.assertCondition(request)
                .ifPresent(errors::add);

        storeRequestCondition.assertCondition(request.getStore())
                .ifPresent(errors::add);
        validateShoppingItemList(request.getShoppingItems())
                .ifPresent(errors::add);

        if (!errors.isEmpty()) {
            throw new BusinessException(formatErrors(errors));
        }
    }

    private Optional<String> validateShoppingItemList(List<MainShoppingItemRequest> itemRequests) {
        for (MainShoppingItemRequest itemRequest : itemRequests) {
            var condition = itemRequestCondition.assertCondition(itemRequest);
            if (condition.isPresent())
                return condition;
        }

        return Optional.empty();
    }

    private String formatErrors(List<String> errors) {
        var stringBuilder = new StringBuilder();

        for (int i = 0; i < errors.size(); i++) {
            stringBuilder.append(errors.get(i));
            if (i == errors.size() - 2)
                stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }
}
