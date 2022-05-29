package mx.kinich49.itemtracker.validators.shoppingitem;

import mx.kinich49.itemtracker.utils.StringUtils;
import mx.kinich49.itemtracker.validators.Condition;
import mx.kinich49.itemtracker.validators.Error;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemNameCondition implements Condition<ShoppingItemRequestParameter> {

    @Override
    public Optional<Error> assertCondition(ShoppingItemRequestParameter param) throws ValidationFlowException {
        if (StringUtils.isNullOrEmptyOrBlank(param.getRequest().getName()))
            return Optional.of(new Error(ErrorConstants.MISSING_ITEM_NAME, "Item is missing Name property."));

        return Optional.empty();
    }
}
