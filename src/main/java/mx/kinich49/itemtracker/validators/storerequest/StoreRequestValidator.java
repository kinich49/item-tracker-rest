package mx.kinich49.itemtracker.validators.storerequest;

import mx.kinich49.itemtracker.validators.AbstractValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreRequestValidator extends AbstractValidator<StoreRequestParameter> {

    @Autowired
    public StoreRequestValidator(StoreRequestConditionProvider conditionProvider) {
        super(conditionProvider);
    }
}
