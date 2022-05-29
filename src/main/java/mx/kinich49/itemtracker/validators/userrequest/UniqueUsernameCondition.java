package mx.kinich49.itemtracker.validators.userrequest;

import mx.kinich49.itemtracker.repositories.UserRepository;
import mx.kinich49.itemtracker.utils.StringUtils;
import mx.kinich49.itemtracker.validators.Condition;
import mx.kinich49.itemtracker.validators.Error;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniqueUsernameCondition implements Condition<UserRequestParameter> {

    private final UserRepository userRepository;

    @Autowired
    public UniqueUsernameCondition(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Error> assertCondition(UserRequestParameter param) throws ValidationFlowException {
        var request = param.getUserRequest();

        if (StringUtils.isNullOrEmptyOrBlank(request.getUsername())) {
            return Optional.empty();
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            return Optional.of(new Error(ErrorConstants.NON_UNIQUE_USERNAME, String.format("Username %s already exists", request.getUsername())));
        }

        return Optional.empty();
    }
}
