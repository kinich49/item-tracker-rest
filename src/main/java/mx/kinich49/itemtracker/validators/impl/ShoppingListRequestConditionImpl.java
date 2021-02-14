package mx.kinich49.itemtracker.validators.impl;

import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.utils.CollectionsUtil;
import mx.kinich49.itemtracker.validators.Condition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ShoppingListRequestConditionImpl implements Condition<MainShoppingListRequest> {

    @Override
    public Optional<String> assertCondition(MainShoppingListRequest request) {
        List<String> errors = new ArrayList<>();

        assertShoppingDate(request)
                .ifPresent(errors::add);
        assertShoppingList(request)
                .ifPresent(errors::add);

        if (errors.isEmpty())
            return Optional.empty();

        return formatErrorMessages(errors);
    }

    private Optional<String> assertShoppingDate(MainShoppingListRequest request) {
        if (request.getShoppingDate() == null)
            return Optional.of("Shopping list must have a date set.");

        return Optional.empty();
    }

    private Optional<String> assertShoppingList(MainShoppingListRequest request) {
        if (CollectionsUtil.isNullOrEmpty(request.getShoppingItems())) {
            return Optional.of("Shopping List must have at least one shopping item.");
        }
        return Optional.empty();
    }

    private Optional<String> formatErrorMessages(List<String> errors) {
        var stringBuilder = new StringBuilder();

        for (int i = 0; i < errors.size(); i++) {
            stringBuilder.append(errors.get(i));
            if (i == errors.size() - 2)
                stringBuilder.append(" ");
        }

        return Optional.of(stringBuilder.toString());
    }
}
