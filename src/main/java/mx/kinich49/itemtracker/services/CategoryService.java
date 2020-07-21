package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.models.database.Category;
import mx.kinich49.itemtracker.models.front.FrontCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<FrontCategory> findAll();

    Optional<FrontCategory> findById(long id);

    List<FrontCategory> findLike(String name);

    Optional<FrontCategory> saveCategory(Category fromRequest);

    Optional<FrontCategory> updateCategory(Category fromRequest);
}
