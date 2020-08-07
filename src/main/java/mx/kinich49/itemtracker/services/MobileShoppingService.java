package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.mobile.responses.MobileShoppingListResponse;
import mx.kinich49.itemtracker.requests.mobile.MobileShoppingListRequest;

public interface MobileShoppingService {

    MobileShoppingListResponse save(MobileShoppingListRequest request) throws UserNotFoundException;

}
