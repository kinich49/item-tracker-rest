package mx.kinich49.itemtracker.validators.impl;

import mx.kinich49.itemtracker.requests.main.CategoryRequest;
import mx.kinich49.itemtracker.utils.NumberUtils;
import mx.kinich49.itemtracker.utils.StringUtils;
import mx.kinich49.itemtracker.validators.Condition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryRequestConditionImpl implements Condition<CategoryRequest> {

    @Override
    public Optional<String> assertCondition(CategoryRequest request) {
        if (request == null)
            return Optional.of("Category must not be null.");

        if (StringUtils.isNullOrEmpty(request.getName()) &&
                NumberUtils.isNullOrZero(request.getId()))
            return Optional.of("Category must have a Name or ID.");

        return Optional.empty();
    }
}
