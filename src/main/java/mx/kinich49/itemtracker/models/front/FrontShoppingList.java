package mx.kinich49.itemtracker.models.front;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.ShoppingList;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public final class FrontShoppingList {

    private final long id;
    private final LocalDate localDate;
    private final FrontStore store;
    private final List<FrontShoppingItem> items;

    public static FrontShoppingList from(ShoppingList shoppingList) {
        if (shoppingList == null)
            return null;

        FrontStore frontStore = FrontStore.from(shoppingList.getStore());

        List<FrontShoppingItem> itemDtos = shoppingList.getShoppingItems()
                .stream()
                .map(FrontShoppingItem::from)
                .collect(Collectors.toList());

        return new FrontShoppingList(shoppingList.getId(),
                shoppingList.getShoppingDate(),
                frontStore,
                itemDtos);
    }
}