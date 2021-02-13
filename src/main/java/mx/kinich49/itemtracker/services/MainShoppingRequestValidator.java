package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;

public interface MainShoppingRequestValidator {

    void validate(MainShoppingListRequest request) throws BusinessException;
}
