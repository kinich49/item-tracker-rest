package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.BrandDto;
import mx.kinich49.itemtracker.dtos.CategoryDto;
import mx.kinich49.itemtracker.dtos.ItemDto;
import mx.kinich49.itemtracker.dtos.SuggestionsDto;
import mx.kinich49.itemtracker.repositories.BrandRepository;
import mx.kinich49.itemtracker.repositories.CategoryRepository;
import mx.kinich49.itemtracker.repositories.ItemRepository;
import mx.kinich49.itemtracker.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final ItemRepository itemRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SuggestionServiceImpl(ItemRepository itemRepository,
                                 BrandRepository brandRepository,
                                 CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<List<ItemDto>> findItemsLike(String name) {
        return Optional.of(itemRepository.findByNameStartsWithIgnoreCase(name))
                .filter(list -> !list.isEmpty())
                .map(ItemDto::from);
    }

    @Override
    public Optional<List<CategoryDto>> findCategoriesLike(String name) {
        return Optional.of(categoryRepository.findByNameStartsWithIgnoreCase(name))
                .filter(list -> !list.isEmpty())
                .map(CategoryDto::from);
    }

    @Override
    public Optional<List<BrandDto>> findBrandsLike(String name) {
        return Optional.of(brandRepository.findByNameStartsWithIgnoreCase(name))
                .filter(list -> !list.isEmpty())
                .map(BrandDto::from);
    }

    @Override
    public Optional<SuggestionsDto> findSuggestionsLike(String name) {
        if (name == null || name.length() < 3)
            return Optional.empty();

        Optional<List<ItemDto>> itemDtos = findItemsLike(name);
        Optional<List<CategoryDto>> categoryDtos = findCategoriesLike(name);
        Optional<List<BrandDto>> brandDtos = findBrandsLike(name);

        if (!itemDtos.isPresent() &&
                !categoryDtos.isPresent() &&
                !brandDtos.isPresent())
            return Optional.empty();

        SuggestionsDto suggestionsDto = new SuggestionsDto();
        suggestionsDto.setItems(itemDtos.orElse(null));
        suggestionsDto.setCategories(categoryDtos.orElse(null));
        suggestionsDto.setBrands(brandDtos.orElse(null));

        return Optional.of(suggestionsDto);
    }
}