package mx.kinich49.itemtracker.validators.shoppinglist;

import mx.kinich49.itemtracker.validators.AbstractConditionProvider;
import mx.kinich49.itemtracker.validators.AbstractValidator;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListOwnershipValidator extends AbstractValidator<ShoppingListOwnershipParameter> {

    public ShoppingListOwnershipValidator(AbstractConditionProvider<ShoppingListOwnershipParameter> conditionProvider) {
        super(conditionProvider);
    }
}
