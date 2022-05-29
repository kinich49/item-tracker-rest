package mx.kinich49.itemtracker.validators;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.services.Validator;

import java.util.function.Consumer;

public abstract class AbstractValidator<T extends ValidatorParameter> implements Validator<T> {

    protected final AbstractConditionProvider<T> conditionProvider;

    public AbstractValidator(AbstractConditionProvider<T> conditionProvider) {
        this.conditionProvider = conditionProvider;
    }

    /**
     * This method executes the validator conditions as provided by
     * its {@link AbstractConditionProvider} implementation.
     */
    @SuppressWarnings("all")
    @Override
    public void validate(T param) throws BusinessException {
        conditionProvider.buildConditions(param);
        var errorBuilder = new ErrorBuilder();

        while (conditionProvider.hasNextCondition()) {
            var pair = conditionProvider.getNextCondition();

            Condition condition = pair.getLeft();
            condition.assertCondition(pair.getRight())
                    .ifPresent(new Consumer<Error>() {
                        @Override
                        public void accept(Error errorWrapper) {
                            errorBuilder.acceptIfAbsent(errorWrapper);
                        }
                    });
        }

        var optionalError = errorBuilder.buildErrorMessage();

        if (optionalError.isPresent()) {
            throw new BusinessException(optionalError.get());
        }
    }
}
