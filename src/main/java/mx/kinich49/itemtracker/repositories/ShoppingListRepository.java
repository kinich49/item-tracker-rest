package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.database.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    List<ShoppingList> findByShoppingDateAndUserId(LocalDate shoppingDate, long userId);

    Optional<ShoppingList> findByIdAndUserId(long shoppingListId, long userId);

    List<ShoppingList> findByUserId(long userId);

}
