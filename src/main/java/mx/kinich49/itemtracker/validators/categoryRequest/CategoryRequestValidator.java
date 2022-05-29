package mx.kinich49.itemtracker.validators.categoryRequest;

import mx.kinich49.itemtracker.validators.AbstractValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestValidator extends AbstractValidator<CategoryRequestParameter> {

    @Autowired
    public CategoryRequestValidator(CategoryRequestConditionProvider conditionProvider) {
        super(conditionProvider);
    }
}
