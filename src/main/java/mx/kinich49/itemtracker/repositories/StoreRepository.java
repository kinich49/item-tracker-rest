package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.database.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByNameStartsWithIgnoreCase(String name);
}
