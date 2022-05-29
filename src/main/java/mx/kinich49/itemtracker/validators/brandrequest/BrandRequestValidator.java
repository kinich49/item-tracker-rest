package mx.kinich49.itemtracker.validators.brandrequest;

import mx.kinich49.itemtracker.validators.AbstractValidator;
import org.springframework.stereotype.Component;

@Component
public class BrandRequestValidator extends AbstractValidator<BrandRequestParameter> {

    public BrandRequestValidator(BrandRequestConditionProvider conditionProvider) {
        super(conditionProvider);
    }
}
