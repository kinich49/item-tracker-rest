package mx.kinich49.itemtracker.validators;

import mx.kinich49.itemtracker.exceptions.BusinessException;

public class ValidationFlowException extends BusinessException {

    public ValidationFlowException(String message) {
        super(message);
    }
}
