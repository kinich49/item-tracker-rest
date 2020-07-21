package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.SuggestionsDto;
import mx.kinich49.itemtracker.models.front.FrontBrand;
import mx.kinich49.itemtracker.models.front.FrontCategory;
import mx.kinich49.itemtracker.models.front.FrontItem;
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
    public List<FrontItem> findItemsLike(String name) {
        return Optional.of(itemRepository.findByNameStartsWithIgnoreCase(name))
                .filter(list -> !list.isEmpty())
                .map(FrontItem::from)
                .orElse(null);
    }

    @Override
    public List<FrontCategory> findCategoriesLike(String name) {
        return Optional.of(categoryRepository.findByNameStartsWithIgnoreCase(name))
                .filter(list -> !list.isEmpty())
                .map(FrontCategory::from)
                .orElse(null);
    }

    @Override
    public List<FrontBrand> findBrandsLike(String name) {
        return Optional.of(brandRepository.findByNameStartsWithIgnoreCase(name))
                .filter(list -> !list.isEmpty())
                .map(FrontBrand::from)
                .orElse(null);
    }

    @Override
    public Optional<SuggestionsDto> findSuggestionsLike(String name) {
        if (name == null || name.length() < 3)
            return Optional.empty();

        List<FrontItem> frontItems = findItemsLike(name);
        List<FrontCategory> frontCategories = findCategoriesLike(name);
        List<FrontBrand> frontBrands = findBrandsLike(name);

        if ((frontItems == null || frontItems.isEmpty()) &&
                (frontCategories == null || frontCategories.isEmpty()) &&
                (frontBrands == null || frontBrands.isEmpty())) {
            return Optional.empty();
        }

        SuggestionsDto.SuggestionsDtoBuilder builder = SuggestionsDto.builder();
        builder.items(frontItems);
        builder.brands(frontBrands);
        builder.categories(frontCategories);

        return Optional.of(builder.build());
    }
}
