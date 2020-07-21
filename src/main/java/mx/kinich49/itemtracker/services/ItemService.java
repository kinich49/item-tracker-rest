package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.ItemAnalyticsDto;
import mx.kinich49.itemtracker.models.front.FrontItem;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<FrontItem> findLike(String name);

    Optional<ItemAnalyticsDto> getAnalyticsFor(long itemId, long userId);

    List<ItemAnalyticsDto> getAnalyticsForCategory(long categoryId, long userId);

    List<ItemAnalyticsDto> getAnalyticsForBrand(long brandId, long userId);
}
