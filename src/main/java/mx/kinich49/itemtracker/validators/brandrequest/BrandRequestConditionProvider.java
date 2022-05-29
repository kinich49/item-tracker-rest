package mx.kinich49.itemtracker.validators.brandrequest;

import mx.kinich49.itemtracker.validators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandRequestConditionProvider extends AbstractConditionProvider<BrandRequestParameter> {


    private final BrandNameIdCondition nameIdCondition;

    @Autowired
    public BrandRequestConditionProvider(BrandNameIdCondition nameIdCondition) {
        this.nameIdCondition = nameIdCondition;
    }

    @Override
    public void buildConditions(BrandRequestParameter parameter) {
        addCondition(nameIdCondition, parameter);
    }
}
