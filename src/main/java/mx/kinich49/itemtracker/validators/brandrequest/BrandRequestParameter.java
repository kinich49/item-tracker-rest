package mx.kinich49.itemtracker.validators.brandrequest;

import lombok.Data;
import mx.kinich49.itemtracker.requests.main.BrandRequest;
import mx.kinich49.itemtracker.validators.ConditionParameter;
import mx.kinich49.itemtracker.validators.ValidatorParameter;

@Data
public class BrandRequestParameter implements ValidatorParameter, ConditionParameter {

    private final BrandRequest request;
}
