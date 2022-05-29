package mx.kinich49.itemtracker.validators.shoppinglist;

import lombok.Data;
import mx.kinich49.itemtracker.validators.ConditionParameter;
import mx.kinich49.itemtracker.validators.ValidatorParameter;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class ShoppingListOwnershipParameter implements ValidatorParameter, ConditionParameter {

    private final Long shoppingListId;
    private final UserDetails userDetails;
}
