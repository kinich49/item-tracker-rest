package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.ItemAnalyticsDto;
import mx.kinich49.itemtracker.dtos.ItemDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<List<ItemDto>> findLike(String name);

    Optional<ItemAnalyticsDto> getAnalyticsFor(long itemId);
}
