package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.CategoryDto;
import mx.kinich49.itemtracker.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDto> findAll();

    Optional<CategoryDto> findById(long id);

    Optional<List<CategoryDto>> findLike(String name);

    Optional<CategoryDto> saveCategory(Category fromRequest);
}
