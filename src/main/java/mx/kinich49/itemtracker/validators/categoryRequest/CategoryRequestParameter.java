package mx.kinich49.itemtracker.validators.categoryRequest;

import lombok.Data;
import mx.kinich49.itemtracker.requests.main.CategoryRequest;
import mx.kinich49.itemtracker.validators.ConditionParameter;
import mx.kinich49.itemtracker.validators.ValidatorParameter;

@Data
public class CategoryRequestParameter implements ValidatorParameter, ConditionParameter {

    private final CategoryRequest request;
}
