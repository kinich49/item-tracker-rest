package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.models.front.FrontShoppingList;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShoppingService {

    Optional<FrontShoppingList> findBy(Long shoppingListId, Long userId);

    Optional<FrontShoppingList> save(MainShoppingListRequest request);

    List<FrontShoppingList> findBy(LocalDate date, Long userId);

    void deleteBy(long shoppingListId);
}
