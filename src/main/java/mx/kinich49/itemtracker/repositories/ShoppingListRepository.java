package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.database.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    List<ShoppingList> findByShoppingDateAndUserId(LocalDate shoppingDate, Long userId);

    Optional<ShoppingList> findByIdAndUserId(long shoppingListId, Long userId);

    List<ShoppingList> findByUserId(Long userId);

}
