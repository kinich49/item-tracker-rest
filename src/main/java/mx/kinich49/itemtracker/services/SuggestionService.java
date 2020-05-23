package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.BrandDto;
import mx.kinich49.itemtracker.dtos.CategoryDto;
import mx.kinich49.itemtracker.dtos.ItemDto;
import mx.kinich49.itemtracker.dtos.SuggestionsDto;

import java.util.List;
import java.util.Optional;

public interface SuggestionService {

    Optional<List<ItemDto>> findItemsLike(String name);

    Optional<List<CategoryDto>> findCategoriesLike(String name);

    Optional<List<BrandDto>> findBrandsLike(String name);

    Optional<SuggestionsDto> findSuggestionsLike(String name);
}