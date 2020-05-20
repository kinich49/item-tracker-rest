package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.ItemAnalyticsDto;
import mx.kinich49.itemtracker.dtos.ItemDto;
import mx.kinich49.itemtracker.dtos.ShoppingItemDto;
import mx.kinich49.itemtracker.models.Store;
import mx.kinich49.itemtracker.repositories.ItemRepository;
import mx.kinich49.itemtracker.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Optional<List<ItemDto>> findLike(String name) {
        if (name == null || name.length() == 0)
            return Optional.empty();

        return Optional.ofNullable(itemRepository.findByNameStartsWithIgnoreCase(name))
                .map(categories ->
                        categories.stream()
                                .map(ItemDto::from)
                                .collect(Collectors.toList()));
    }

    @Override
    public Optional<ItemAnalyticsDto> getAnalyticsFor(long itemId) {

        if (!itemRepository.existsById(itemId))
            return Optional.empty();

        ItemAnalyticsDto dto = new ItemAnalyticsDto();
        itemRepository.findLatestStoreAndShoppingDateAndPrice(itemId)
                .ifPresent(tuple -> {
                    Optional.ofNullable(tuple.get(0, Store.class))
                            .map(Store::getName)
                            .ifPresent(dto::setLatestStore);

                    Optional.ofNullable(tuple.get(1, LocalDate.class))
                            .map(LocalDate::toString)
                            .ifPresent(dto::setLatestDate);

                    Optional.ofNullable(tuple.get(2, Integer.class))
                            .map(latestPrice -> ShoppingItemDto.transformAndFormatPrice(latestPrice, "MXN"))
                            .ifPresent(dto::setLatestPrice);
                });

        List<Tuple> averages = itemRepository.findAverageUnitPriceAndCurrency(itemId);
        if (averages != null && !averages.isEmpty()) {
            averages.stream()
                    .filter(tuple -> tuple.get(1, String.class).equalsIgnoreCase("MXN"))
                    .findFirst()
                    .ifPresent(tuple -> {
                        Double averageUnitPrice = tuple.get(0, Double.class);
                        String currency = tuple.get(1, String.class);
                        String average = ShoppingItemDto.transformAndFormatPrice(averageUnitPrice, currency);
                        dto.setAveragePrice(average);
                    });
        }
        return Optional.of(dto);
    }
}
