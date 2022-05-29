package mx.kinich49.itemtracker.validators.shoppinglist;

import mx.kinich49.itemtracker.validators.Condition;
import mx.kinich49.itemtracker.validators.Error;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingDateCondition implements Condition<ShoppingListParameter> {

    @Override
    public Optional<Error> assertCondition(ShoppingListParameter param) throws ValidationFlowException {
        if (param.getRequest().getShoppingDate() == null) {
            return Optional.of(new Error(ErrorConstants.MISSING_SHOPPING_DATE, "Shopping List is missing date"));
        }

        return Optional.empty();
    }
}
