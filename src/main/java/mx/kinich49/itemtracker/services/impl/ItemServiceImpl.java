package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.ItemAnalyticsDto;
import mx.kinich49.itemtracker.models.database.Item;
import mx.kinich49.itemtracker.models.database.Store;
import mx.kinich49.itemtracker.models.front.FrontItem;
import mx.kinich49.itemtracker.models.front.FrontShoppingItem;
import mx.kinich49.itemtracker.repositories.ItemRepository;
import mx.kinich49.itemtracker.services.ItemService;
import mx.kinich49.itemtracker.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final SuggestionService suggestionService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           SuggestionService suggestionService) {
        this.itemRepository = itemRepository;
        this.suggestionService = suggestionService;
    }

    @Override
    public List<FrontItem> findLike(String name) {
        return suggestionService.findItemsLike(name);
    }

    @Override
    public Optional<ItemAnalyticsDto> getAnalyticsFor(Long itemId, Long userId) {
        if (!itemRepository.existsById(itemId))
            return Optional.empty();

        List<Tuple> averageTuples = itemRepository.findAverageUnitPriceAndCurrency(itemId, userId);
        List<Tuple> latestTuples = new ArrayList<>();

        itemRepository.findLatestStoreAndShoppingDateAndPrice(itemId, userId)
                .ifPresent(latestTuples::add);

        return Optional.ofNullable(getAnalyticsFor(averageTuples, latestTuples))
                .map(analytics -> analytics.get(0));
    }

    @Override
    public List<ItemAnalyticsDto> getAnalyticsForCategory(Long categoryId, Long userId) {
        return getAnalyticsFor(itemRepository.findAverageUnitPriceAndCurrencyForCategory(categoryId, 1L),
                itemRepository.findLatestStoreAndShoppingDateAndPriceForCategory(categoryId, userId));
    }

    @Override
    public List<ItemAnalyticsDto> getAnalyticsForBrand(Long brandId, Long userId) {
        return getAnalyticsFor(itemRepository.findAverageUnitPriceAndCurrencyForBrand(brandId, userId),
                itemRepository.findLatestStoreAndShoppingDateAndPriceForBrand(brandId, userId));
    }

    private List<ItemAnalyticsDto> getAnalyticsFor(Collection<Tuple> averageTuples,
                                                   Collection<Tuple> latestTuples) {
        if ((averageTuples == null || averageTuples.isEmpty()) &&
                (latestTuples == null || latestTuples.isEmpty()))
            return null;

        Map<Item, Tuple[]> map = new HashMap<>();

        Optional.ofNullable(averageTuples)
                .ifPresent(results -> results.forEach(tuple -> {
                    Item item = tuple.get(0, Item.class);
                    Tuple[] tuples = new Tuple[2];
                    tuples[0] = tuple;
                    map.putIfAbsent(item, tuples);
                }));

        Optional.ofNullable(latestTuples)
                .ifPresent(results -> results.forEach(tuple -> {
                    Item item = tuple.get(0, Item.class);
                    if (map.containsKey(item)) {
                        Tuple[] tuples = map.get(item);
                        tuples[1] = tuple;
                        map.put(item, tuples);
                    } else {
                        Tuple[] tuples = new Tuple[2];
                        tuples[1] = tuple;
                        map.putIfAbsent(item, tuples);
                    }
                }));

        return map.entrySet().stream()
                .map(entrySet -> {
                    Item item = entrySet.getKey();
                    Tuple[] tuples = entrySet.getValue();
                    return buildAnalyticsDto(item, tuples[0], tuples[1]);
                })
                .collect(Collectors.toList());
    }

    /**
     * @param averageTuple index 0
     * @param latestTuple  index 1
     */
    private ItemAnalyticsDto buildAnalyticsDto(Item item,
                                               Tuple averageTuple,
                                               Tuple latestTuple) {
        ItemAnalyticsDto.ItemAnalyticsDtoBuilder builder = ItemAnalyticsDto.builder();
        builder.item(FrontItem.from(item));
        if (averageTuple != null) {
            Double averagePrice = averageTuple.get(1, Double.class);
            String currency = averageTuple.get(2, String.class);
            builder.averagePrice(FrontShoppingItem.transformAndFormatPrice(averagePrice, currency));
        }

        if (latestTuple != null) {
            Optional.ofNullable(latestTuple.get(1, Store.class))
                    .map(Store::getName)
                    .ifPresent(builder::latestStore);

            Optional.ofNullable(latestTuple.get(2, LocalDate.class))
                    .map(LocalDate::toString)
                    .ifPresent(builder::latestDate);

            Optional.ofNullable(latestTuple.get(3, Integer.class))
                    .map(latestPrice -> FrontShoppingItem.transformAndFormatPrice(latestPrice, "MXN"))
                    .ifPresent(builder::latestPrice);
        }

        return builder.build();
    }
}
