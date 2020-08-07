package mx.kinich49.itemtracker.models.mobile.responses;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Item;
import mx.kinich49.itemtracker.models.database.ShoppingItem;
import org.javatuples.Tuple;

@Data
public class MobileShoppingItemResponse {

    private final long id;
    private final Long mobileId;
    private final String currency;
    private final Double quantity;
    private final String unit;
    private final int unitPrice;
    private final long shoppingListId;
    private final MobileItemResponse item;

    public static MobileShoppingItemResponse from(ShoppingItem shoppingItem,
                                                  Long shoppingItemMobileId,
                                                  long shoppingListId,
                                                  Item item,
                                                  Long itemMobileId,
                                                  Long brandMobileId,
                                                  Long categoryMobileId) {
        if (shoppingItem == null)
            return null;

        MobileItemResponse itemResponse = MobileItemResponse.from(item, itemMobileId, brandMobileId, categoryMobileId);

        return new MobileShoppingItemResponse(shoppingItem.getId(), shoppingItemMobileId,
                shoppingItem.getCurrency(), shoppingItem.getQuantity(), shoppingItem.getUnit(),
                shoppingItem.getUnitPrice(), shoppingListId, itemResponse);
    }

    public static MobileShoppingItemResponse from(Tuple tuple, long shoppingListId) {
        if (tuple == null || tuple.getSize() == 0)
            return null;

        ShoppingItem shoppingItem = (ShoppingItem) tuple.getValue(0);
        Long shoppingItemMobileId = (Long) tuple.getValue(1);
        Item item = (Item) tuple.getValue(2);
        Long itemMobileId = (Long) tuple.getValue(3);
        Long brandMobileId = (Long) tuple.getValue(4);
        Long categoryMobileId = (Long) tuple.getValue(5);

        return from(shoppingItem, shoppingItemMobileId, shoppingListId, item, itemMobileId,
                brandMobileId, categoryMobileId);
    }
}
