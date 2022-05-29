package mx.kinich49.itemtracker.validators;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NonNullRequestCondition implements Condition<RequestParameter> {

    @Override
    public Optional<Error> assertCondition(RequestParameter param) throws ValidationFlowException {
        if (param.getRequest() == null) {
            throw new ValidationFlowException("Request is null");
        }

        return Optional.empty();
    }
}
