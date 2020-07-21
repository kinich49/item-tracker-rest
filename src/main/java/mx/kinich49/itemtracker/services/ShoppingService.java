package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.front.FrontShoppingList;
import mx.kinich49.itemtracker.requests.ShoppingListRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShoppingService {

    Optional<FrontShoppingList> findBy(long shoppingListId, long userId);

    Optional<FrontShoppingList> save(ShoppingListRequest request) throws UserNotFoundException;

    List<FrontShoppingList> findBy(LocalDate date, long userId);

    void deleteBy(long shoppingListId);
}
