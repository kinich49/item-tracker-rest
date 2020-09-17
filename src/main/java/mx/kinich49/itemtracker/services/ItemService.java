package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.ItemAnalyticsDto;
import mx.kinich49.itemtracker.models.front.FrontItem;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<FrontItem> findLike(String name);

    Optional<ItemAnalyticsDto> getAnalyticsFor(Long itemId, Long userId);

    List<ItemAnalyticsDto> getAnalyticsForCategory(Long categoryId, Long userId);

    List<ItemAnalyticsDto> getAnalyticsForBrand(Long brandId, Long userId);
}
