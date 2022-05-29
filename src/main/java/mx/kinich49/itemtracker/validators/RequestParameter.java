package mx.kinich49.itemtracker.validators;

import lombok.Data;

@Data
public class RequestParameter implements ConditionParameter {

    private final Object request;
}
