package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.database.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {

}
