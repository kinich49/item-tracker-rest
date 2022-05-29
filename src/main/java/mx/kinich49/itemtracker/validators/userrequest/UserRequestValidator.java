package mx.kinich49.itemtracker.validators.userrequest;

import mx.kinich49.itemtracker.validators.AbstractValidator;
import org.springframework.stereotype.Component;

@Component
public class UserRequestValidator extends AbstractValidator<UserRequestParameter> {

    public UserRequestValidator(UserRequestConditionProvider conditionProvider) {
        super(conditionProvider);
    }
}
