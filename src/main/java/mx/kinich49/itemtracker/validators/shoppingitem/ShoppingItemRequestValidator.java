package mx.kinich49.itemtracker.validators.shoppingitem;

import mx.kinich49.itemtracker.validators.AbstractValidator;
import org.springframework.stereotype.Component;

@Component
public class ShoppingItemRequestValidator extends AbstractValidator<ShoppingItemRequestParameter> {

    public ShoppingItemRequestValidator(ShoppingItemConditionProvider conditionProvider) {
        super(conditionProvider);
    }

}
