package mx.kinich49.itemtracker.validators.categoryRequest;

import mx.kinich49.itemtracker.validators.AbstractConditionProvider;
import mx.kinich49.itemtracker.validators.NonNullRequestCondition;
import mx.kinich49.itemtracker.validators.RequestParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestConditionProvider extends AbstractConditionProvider<CategoryRequestParameter> {

    private final CategoryNameIdCondition nameIdCondition;
    private final NonNullRequestCondition nonNullRequestCondition;

    @Autowired
    public CategoryRequestConditionProvider(CategoryNameIdCondition nameIdCondition,
                                            NonNullRequestCondition nonNullRequestCondition) {
        this.nameIdCondition = nameIdCondition;
        this.nonNullRequestCondition = nonNullRequestCondition;
    }

    @Override
    public void buildConditions(CategoryRequestParameter parameter) {
        var requestParameter = new RequestParameter(parameter.getRequest());

        addCondition(nonNullRequestCondition, requestParameter);
        addCondition(nameIdCondition, parameter);
    }
}
