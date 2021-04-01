package mx.kinich49.itemtracker.validators.impl;

import mx.kinich49.itemtracker.requests.main.BrandRequest;
import mx.kinich49.itemtracker.utils.NumberUtils;
import mx.kinich49.itemtracker.utils.StringUtils;
import mx.kinich49.itemtracker.validators.Condition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BrandRequestConditionImpl implements Condition<BrandRequest> {

    @Override
    public Optional<String> assertCondition(BrandRequest request) {
        //A Brandless item is permitted
        if(request == null)
            return Optional.empty();

        if (StringUtils.isNullOrEmpty(request.getName()) &&
                NumberUtils.isNullOrZero(request.getId()))
            return Optional.of("Brand must have a name or a valid ID.");

        return Optional.empty();
    }

}
