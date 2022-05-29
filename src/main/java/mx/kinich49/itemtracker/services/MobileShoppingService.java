package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.models.mobile.MobileShoppingList;
import mx.kinich49.itemtracker.requests.mobile.MobileShoppingListRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface MobileShoppingService {

    MobileShoppingList save(MobileShoppingListRequest request, UserDetails userDetails) throws BusinessException;

}
