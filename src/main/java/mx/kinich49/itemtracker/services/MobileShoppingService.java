package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.mobile.MobileShoppingList;
import mx.kinich49.itemtracker.requests.mobile.MobileShoppingListRequest;

public interface MobileShoppingService {

    MobileShoppingList save(MobileShoppingListRequest request) throws UserNotFoundException;

}
