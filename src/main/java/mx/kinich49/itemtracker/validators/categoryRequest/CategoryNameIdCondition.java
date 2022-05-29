package mx.kinich49.itemtracker.validators.categoryRequest;

import mx.kinich49.itemtracker.utils.NumberUtils;
import mx.kinich49.itemtracker.utils.StringUtils;
import mx.kinich49.itemtracker.validators.Condition;
import mx.kinich49.itemtracker.validators.Error;
import mx.kinich49.itemtracker.validators.ErrorConstants;
import mx.kinich49.itemtracker.validators.ValidationFlowException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryNameIdCondition implements Condition<CategoryRequestParameter> {

    @Override
    public Optional<Error> assertCondition(CategoryRequestParameter param) throws ValidationFlowException {
        var request = param.getRequest();

        if (request == null) {
            throw new ValidationFlowException("Request is null");
        }

        if (StringUtils.isNullOrEmptyOrBlank(request.getName()) &&
                NumberUtils.isNullOrZero(request.getId())) {
            return Optional.of(new Error(ErrorConstants.REQUEST_NAME_IS_NULL_AND_ID_IS_NULL,
                    "Name and Id are null"));
        }

        return Optional.empty();
    }
}
