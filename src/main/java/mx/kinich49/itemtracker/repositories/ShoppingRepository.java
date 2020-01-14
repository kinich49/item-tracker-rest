package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
}
