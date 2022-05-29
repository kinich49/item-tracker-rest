package mx.kinich49.itemtracker.validators.shoppinglist;

import mx.kinich49.itemtracker.validators.AbstractConditionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListOwnershipConditionProvider extends AbstractConditionProvider<ShoppingListOwnershipParameter> {

    private final ShoppingListBelongsToUserCondition belongsToUserCondition;

    @Autowired
    public ShoppingListOwnershipConditionProvider(ShoppingListBelongsToUserCondition belongsToUserCondition) {
        this.belongsToUserCondition = belongsToUserCondition;
    }

    @Override
    public void buildConditions(ShoppingListOwnershipParameter parameter) {
        addCondition(belongsToUserCondition, parameter);
    }
}
