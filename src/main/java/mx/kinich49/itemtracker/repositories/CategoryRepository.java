package mx.kinich49.itemtracker.repositories;

import mx.kinich49.itemtracker.models.database.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameStartsWithIgnoreCase(String name);
}
