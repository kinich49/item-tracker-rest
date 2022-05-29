package mx.kinich49.itemtracker.validators.storerequest;

import mx.kinich49.itemtracker.validators.AbstractConditionProvider;
import mx.kinich49.itemtracker.validators.NonNullRequestCondition;
import mx.kinich49.itemtracker.validators.RequestParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreRequestConditionProvider extends AbstractConditionProvider<StoreRequestParameter> {

    private final NonNullRequestCondition nonNullRequestCondition;
    private final StoreNameIdCondition nameIdCondition;

    @Autowired
    public StoreRequestConditionProvider(NonNullRequestCondition nonNullRequestCondition,
                                         StoreNameIdCondition nameIdCondition) {
        this.nonNullRequestCondition = nonNullRequestCondition;
        this.nameIdCondition = nameIdCondition;
    }

    @Override
    public void buildConditions(StoreRequestParameter parameter) {
        var requestParameter = new RequestParameter(parameter.getRequest());

        addCondition(nonNullRequestCondition, requestParameter);
        addCondition(nameIdCondition, parameter);
    }
}
