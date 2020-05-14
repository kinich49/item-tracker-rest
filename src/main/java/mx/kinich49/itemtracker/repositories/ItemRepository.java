package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNameStartsWithIgnoreCase(String name);
}
