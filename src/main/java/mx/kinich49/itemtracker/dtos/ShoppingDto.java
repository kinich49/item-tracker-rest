package mx.kinich49.itemtracker.dtos;

import lombok.Data;
import mx.kinich49.itemtracker.models.Shopping;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ShoppingDto {

    private final long id;
    private final LocalDate localDate;
    private final StoreDto store;
    private final List<ShoppingItemDto> items;

    public static ShoppingDto from(Shopping shopping) {
        if (shopping == null)
            return null;

        StoreDto storeDto = StoreDto.from(shopping.getStore());

        List<ShoppingItemDto> itemDtos = shopping.getShoppingItems()
                .stream()
                .map(ShoppingItemDto::from)
                .collect(Collectors.toList());

        return new ShoppingDto(shopping.getId(),
                shopping.getShoppingDate(),
                storeDto,
                itemDtos);
    }
}