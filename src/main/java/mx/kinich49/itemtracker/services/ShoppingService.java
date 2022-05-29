package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.models.front.FrontShoppingList;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShoppingService {

    Optional<FrontShoppingList> findBy(Long shoppingListId,
                                       UserDetails userDetails) throws BusinessException;

    Optional<FrontShoppingList> save(MainShoppingListRequest request,
                                     UserDetails userDetails) throws BusinessException;

    List<FrontShoppingList> findBy(LocalDate date,
                                   UserDetails userDetails);

    void deleteBy(long shoppingListId,
                  UserDetails userDetails) throws BusinessException;
}
