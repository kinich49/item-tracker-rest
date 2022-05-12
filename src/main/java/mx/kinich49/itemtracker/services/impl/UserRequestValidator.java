package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.requests.main.UserRequest;
import mx.kinich49.itemtracker.services.Validator;
import mx.kinich49.itemtracker.validators.impl.UniqueUsernameCondition;
import mx.kinich49.itemtracker.validators.impl.UserRequestCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRequestValidator implements Validator<UserRequest> {

    private final UserRequestCondition userRequestCondition;
    private final UniqueUsernameCondition uniqueUsernameCondition;

    @Autowired
    public UserRequestValidator(UserRequestCondition userRequestCondition,
                                UniqueUsernameCondition uniqueUsernameCondition) {
        this.userRequestCondition = userRequestCondition;
        this.uniqueUsernameCondition = uniqueUsernameCondition;
    }

    @Override
    public void validate(UserRequest param) throws BusinessException {
        Optional<String> result  = userRequestCondition.assertCondition(param)
                .or(() -> uniqueUsernameCondition.assertCondition(param));

        if (result.isPresent()) {
            throw new BusinessException(result.get());
        }
    }
}
