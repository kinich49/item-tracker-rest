package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    List<ShoppingList> findByShoppingDate(LocalDate shoppingDate);


}
