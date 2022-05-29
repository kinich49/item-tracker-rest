package mx.kinich49.itemtracker.validators.shoppinglist;

import mx.kinich49.itemtracker.validators.AbstractValidator;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListValidator extends AbstractValidator<ShoppingListParameter> {

    public ShoppingListValidator(ShoppingListConditionProvider conditionProvider) {
        super(conditionProvider);
    }
}
