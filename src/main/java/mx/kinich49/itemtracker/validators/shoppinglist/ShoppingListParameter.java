package mx.kinich49.itemtracker.validators.shoppinglist;

import lombok.Data;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.validators.ConditionParameter;
import mx.kinich49.itemtracker.validators.ValidatorParameter;

@Data
public class ShoppingListParameter implements ValidatorParameter, ConditionParameter {

    private final MainShoppingListRequest request;
}
