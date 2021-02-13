package mx.kinich49.itemtracker.validators.impl;

import mx.kinich49.itemtracker.requests.main.StoreRequest;
import mx.kinich49.itemtracker.validators.StoreRequestValidationCondition;
import mx.kinich49.itemtracker.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StoreRequestValidationConditionImpl implements StoreRequestValidationCondition {

    @Override
    public Optional<String> validate(StoreRequest store) {
        if (store == null)
            return Optional.of("Store must not be null");

        if(StringUtils.isNullOrEmpty(store.getName()))
            return Optional.of("Store must have a name");

        return Optional.empty();
    }
}
