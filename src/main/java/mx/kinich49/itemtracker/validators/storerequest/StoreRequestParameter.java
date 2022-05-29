package mx.kinich49.itemtracker.validators.storerequest;

import lombok.Data;
import mx.kinich49.itemtracker.requests.main.StoreRequest;
import mx.kinich49.itemtracker.validators.ConditionParameter;
import mx.kinich49.itemtracker.validators.ValidatorParameter;

@Data
public class StoreRequestParameter implements ConditionParameter, ValidatorParameter {

    private final StoreRequest request;
}
