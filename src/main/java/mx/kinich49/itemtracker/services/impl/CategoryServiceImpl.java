package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.CategoryDto;
import mx.kinich49.itemtracker.models.Category;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import mx.kinich49.itemtracker.services.CategoryService;
import mx.kinich49.itemtracker.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unused")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SuggestionService suggestionService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               SuggestionService suggestionService) {
        this.categoryRepository = categoryRepository;
        this.suggestionService = suggestionService;
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
    public List<CategoryDto> findLike(String name) {
        return suggestionService.findCategoriesLike(name);
    }

    @Override
    public Optional<CategoryDto> saveCategory(Category fromRequest) {
        if (fromRequest == null)
            return Optional.empty();

        return Optional.of(categoryRepository.save(fromRequest))
                .map(CategoryDto::from);
    }

    @Override
    public Optional<CategoryDto> updateCategory(Category fromRequest) {
        if (fromRequest == null)
            return Optional.empty();

        Optional<Category> optCategory = categoryRepository.findById(fromRequest.getId());
        if (!optCategory.isPresent())
            return Optional.empty();

        Category fromPersistence = optCategory.get();

        fromPersistence.setName(fromRequest.getName());
        return Optional.of(categoryRepository.save(fromPersistence))
                .map(CategoryDto::from);
    }
}
