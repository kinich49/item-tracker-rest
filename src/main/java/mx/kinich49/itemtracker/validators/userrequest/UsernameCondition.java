package mx.kinich49.itemtracker.validators.userrequest;

import mx.kinich49.itemtracker.utils.StringUtils;
import mx.kinich49.itemtracker.validators.Condition;
import mx.kinich49.itemtracker.validators.Error;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsernameCondition implements Condition<UserRequestParameter> {

    @Override
    public Optional<Error> assertCondition(UserRequestParameter param) throws ValidationFlowException {
        var request = param.getUserRequest();
        if (StringUtils.isNullOrEmptyOrBlank(request.getUsername())) {
            return Optional.of(new Error(ErrorConstants.USERNAME_NULL_OR_EMPTY, "username can't be null or empty"));
        }

        return Optional.empty();
    }
}
