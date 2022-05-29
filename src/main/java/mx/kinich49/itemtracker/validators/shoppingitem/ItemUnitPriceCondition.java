package mx.kinich49.itemtracker.validators.shoppingitem;

import mx.kinich49.itemtracker.utils.NumberUtils;
import mx.kinich49.itemtracker.validators.Condition;
import mx.kinich49.itemtracker.validators.Error;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemUnitPriceCondition implements Condition<ShoppingItemRequestParameter> {

    @Override
    public Optional<Error> assertCondition(ShoppingItemRequestParameter param) throws ValidationFlowException {
        if (NumberUtils.isNullOrZero(param.getRequest().getUnitPrice()))
            return Optional.of(new Error(ErrorConstants.MISSING_ITEM_UNIT_PRICE, "Item is missing unit price property."));

        return Optional.empty();
    }
}
