package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.SuggestionsDto;
import mx.kinich49.itemtracker.models.front.FrontBrand;
import mx.kinich49.itemtracker.models.front.FrontCategory;
import mx.kinich49.itemtracker.models.front.FrontItem;

import java.util.List;
import java.util.Optional;

public interface SuggestionService {

    List<FrontItem> findItemsLike(String name);

    List<FrontCategory> findCategoriesLike(String name);

    List<FrontBrand> findBrandsLike(String name);

    Optional<SuggestionsDto> findSuggestionsLike(String name);
}
