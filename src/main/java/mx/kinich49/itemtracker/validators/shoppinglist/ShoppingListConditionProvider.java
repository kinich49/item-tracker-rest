package mx.kinich49.itemtracker.validators.shoppinglist;

import mx.kinich49.itemtracker.validators.AbstractConditionProvider;
import mx.kinich49.itemtracker.validators.NonNullRequestCondition;
import mx.kinich49.itemtracker.validators.RequestParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListConditionProvider extends AbstractConditionProvider<ShoppingListParameter> {

    private final NonNullRequestCondition nonNullRequestCondition;
    private final ShoppingDateCondition shoppingDateCondition;

    @Autowired
    public ShoppingListConditionProvider(NonNullRequestCondition nonNullRequestCondition,
                                         ShoppingDateCondition shoppingDateCondition) {
        this.nonNullRequestCondition = nonNullRequestCondition;
        this.shoppingDateCondition = shoppingDateCondition;
    }

    @Override
    public void buildConditions(ShoppingListParameter parameter) {
        var requestParameter = new RequestParameter(parameter.getRequest());
        addCondition(nonNullRequestCondition, requestParameter);
        addCondition(shoppingDateCondition, parameter);
    }
}
