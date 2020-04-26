package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.CategoryDto;
import mx.kinich49.itemtracker.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDto> findAll();

    Optional<CategoryDto> findById(long id);

    Optional<CategoryDto> saveCategory(Category fromRequest);
}
