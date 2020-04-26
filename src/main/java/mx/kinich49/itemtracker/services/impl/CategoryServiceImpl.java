package mx.kinich49.itemtracker.sevices.impl;

import mx.kinich49.itemtracker.models.Category;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import mx.kinich49.itemtracker.sevices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> saveCategory(Category fromRequest) {
        if (fromRequest == null)
            return Optional.empty();

        return Optional.of(categoryRepository.save(fromRequest));
    }
}
