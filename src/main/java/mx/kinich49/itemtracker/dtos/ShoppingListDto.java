package mx.kinich49.itemtracker.dtos;

import lombok.Data;
import mx.kinich49.itemtracker.models.ShoppingList;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public final class ShoppingListDto {

    private final long id;
    private final LocalDate localDate;
    private final StoreDto store;
    private final List<ShoppingItemDto> items;

    public static ShoppingListDto from(ShoppingList shoppingList) {
        if (shoppingList == null)
            return null;

        StoreDto storeDto = StoreDto.from(shoppingList.getStore());

        List<ShoppingItemDto> itemDtos = shoppingList.getShoppingItems()
                .stream()
                .map(ShoppingItemDto::from)
                .collect(Collectors.toList());

        return new ShoppingListDto(shoppingList.getId(),
                shoppingList.getShoppingDate(),
                storeDto,
                itemDtos);
    }
}