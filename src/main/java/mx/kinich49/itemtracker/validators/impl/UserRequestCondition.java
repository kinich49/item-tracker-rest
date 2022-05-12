package mx.kinich49.itemtracker.validators.impl;

import mx.kinich49.itemtracker.requests.main.UserRequest;
import mx.kinich49.itemtracker.utils.StringUtils;
import mx.kinich49.itemtracker.validators.Condition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRequestCondition implements Condition<UserRequest> {

    @Override
    public Optional<String> assertCondition(UserRequest param) {
        if (param == null)
            return Optional.of("Request can't be null");

        if (StringUtils.isNullOrEmptyOrBlank(param.getPassword())) {
            return Optional.of("Password can't be null or empty");
        }

        if (StringUtils.isNullOrEmptyOrBlank(param.getUsername())) {
            return Optional.of("username can't be null or empty");
        }

        return Optional.empty();
    }
}
