package mx.kinich49.itemtracker.validators.shoppingitem;

import lombok.Data;
import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.validators.ConditionParameter;
import mx.kinich49.itemtracker.validators.ValidatorParameter;

@Data
public class ShoppingItemRequestParameter implements ConditionParameter, ValidatorParameter {

    private final MainShoppingItemRequest request;
}
