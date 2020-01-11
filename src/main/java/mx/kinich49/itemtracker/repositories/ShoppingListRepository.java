package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
}
