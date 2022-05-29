package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.validators.Condition;
import mx.kinich49.itemtracker.validators.ValidatorParameter;

/**
 * Implementations of this interface will have at least one {@link Condition}.
 * If the implementation has more than one, then it is understood those
 * conditions are thigh-coupled.
 * <p>
 * If at least one condition is not satisfied,
 * then a {@link BusinessException} <b>must</b> be thrown.
 * The exception's message should explain the error in a user-friendly manner
 * <p>
 * If all conditions are met, then the parameter is declared "valid",
 * meaning all Business Rules, requirements or demands are satisfied,
 * and the validator client can continue its process
 */
public interface Validator<T extends ValidatorParameter> {

    /**
     * @param param the param to assert whether it meets all related conditions
     * @throws BusinessException if any {@link Condition}
     *                           is not satisfied
     */
    void validate(T param) throws BusinessException;
}
