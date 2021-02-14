package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.BusinessException;

/**
 * An interface to declare a combination of {@link mx.kinich49.itemtracker.validators.Condition}
 * <p>
 * If at least one condition is not satisfied, then a {@link BusinessException} should be thrown.
 * The exception's message should explain the error in a user-friendly manner
 *
 * @param <T>
 */
public interface Validator<T> {

    /**
     * @param param the param to assert whether it meets all related conditions
     * @throws BusinessException if any {@link mx.kinich49.itemtracker.validators.Condition}
     *                           is not satisfied
     */
    void validate(T param) throws BusinessException;
}
