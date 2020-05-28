package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.models.*;
import mx.kinich49.itemtracker.requests.ShoppingListRequest;

public interface DtoEntityService {

    ShoppingList from(ShoppingListRequest request);

    ShoppingItem from(ShoppingListRequest.ShoppingItem request,
                      Brand brand,
                      Category category);

    Category from(ShoppingListRequest.Category request);

    Brand from(ShoppingListRequest.Brand request);

    Store from(ShoppingListRequest.Store request);
}
