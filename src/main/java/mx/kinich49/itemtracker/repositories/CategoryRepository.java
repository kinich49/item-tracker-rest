package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.database.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    List<Category> findByNameStartsWithIgnoreCase(String name);
}
