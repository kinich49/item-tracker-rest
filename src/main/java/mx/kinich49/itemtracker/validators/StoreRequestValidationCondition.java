package mx.kinich49.itemtracker.validators;

import mx.kinich49.itemtracker.requests.main.StoreRequest;

import java.util.Optional;

public interface StoreRequestValidationCondition {

    Optional<String> validate(StoreRequest request);
}
