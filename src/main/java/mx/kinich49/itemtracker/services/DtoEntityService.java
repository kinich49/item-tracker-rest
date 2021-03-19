package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.models.database.*;
import mx.kinich49.itemtracker.requests.BaseShoppingItemRequest;
import mx.kinich49.itemtracker.requests.BaseShoppingListRequest;
import mx.kinich49.itemtracker.requests.main.BrandRequest;
import mx.kinich49.itemtracker.requests.main.CategoryRequest;
import mx.kinich49.itemtracker.requests.main.StoreRequest;

public interface DtoEntityService {

    <T extends BaseShoppingListRequest> ShoppingList from(T request);

    <T extends BaseShoppingItemRequest> ShoppingItem shoppingItemFrom(T request);

    <T extends BaseShoppingItemRequest> Item itemFrom(T request);

    Category from(CategoryRequest request);

    Brand from(BrandRequest request);

    Store from(StoreRequest request);
}
