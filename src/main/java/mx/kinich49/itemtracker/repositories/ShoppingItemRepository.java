package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {

}
