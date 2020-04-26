package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.CategoryDto;
import mx.kinich49.itemtracker.models.Category;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import mx.kinich49.itemtracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unused")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream().parallel()
                .map(CategoryDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> findById(long id) {
        return categoryRepository.findById(id)
                .map(CategoryDto::from);
    }

    @Override
    public Optional<CategoryDto> saveCategory(Category fromRequest) {
        if (fromRequest == null)
            return Optional.empty();

        return Optional.of(categoryRepository.save(fromRequest))
                .map(CategoryDto::from);
    }
}
