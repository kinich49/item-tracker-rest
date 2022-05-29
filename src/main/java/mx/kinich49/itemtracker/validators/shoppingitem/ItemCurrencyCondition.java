package mx.kinich49.itemtracker.validators.shoppingitem;

import mx.kinich49.itemtracker.utils.StringUtils;
import mx.kinich49.itemtracker.validators.Condition;
import mx.kinich49.itemtracker.validators.Error;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemCurrencyCondition implements Condition<ShoppingItemRequestParameter> {

    @Override
    public Optional<Error> assertCondition(ShoppingItemRequestParameter param) throws ValidationFlowException {
        if (StringUtils.isNullOrEmptyOrBlank(param.getRequest().getCurrency()))
            return Optional.of(new Error(ErrorConstants.MISSING_ITEM_CURRENCY, "Item is missing Currency property."));

        return Optional.empty();
    }
}
