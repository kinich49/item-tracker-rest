package mx.kinich49.itemtracker.validators.userrequest;

import lombok.Data;
import mx.kinich49.itemtracker.requests.main.UserRequest;
import mx.kinich49.itemtracker.validators.ConditionParameter;
import mx.kinich49.itemtracker.validators.ValidatorParameter;

@Data
public class UserRequestParameter implements ValidatorParameter, ConditionParameter {

    private final UserRequest userRequest;
}
