package mx.kinich49.itemtracker.validators.impl;

import mx.kinich49.itemtracker.requests.main.StoreRequest;
import mx.kinich49.itemtracker.utils.NumberUtils;
import mx.kinich49.itemtracker.utils.StringUtils;
import mx.kinich49.itemtracker.validators.Condition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StoreRequestConditionImpl implements Condition<StoreRequest> {

    @Override
    public Optional<String> assertCondition(StoreRequest request) {
        if (request == null)
            return Optional.of("Store must not be null.");

        if (StringUtils.isNullOrEmpty(request.getName()) &&
                NumberUtils.isNullOrZero(request.getId()))
            return Optional.of("Store must have a name or a valid Id");

        return Optional.empty();
    }

}
