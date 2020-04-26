package mx.kinich49.itemtracker.sevices;

import mx.kinich49.itemtracker.models.Category;

import java.util.Optional;

public interface CategoryService {

    Optional<Category> saveCategory(Category fromRequest);
}
