package mx.kinich49.itemtracker.models.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Item;
import mx.kinich49.itemtracker.models.database.ShoppingItem;
import org.javatuples.Tuple;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MobileShoppingItem {

    private final long remoteId;
    private final Long mobileId;
    private final String currency;
    private final Double quantity;
    private final String unit;
    private final int unitPrice;
    private final long shoppingListId;
    private final MobileItem item;

    public static MobileShoppingItem from(ShoppingItem shoppingItem,
                                          Long shoppingItemMobileId,
                                          long shoppingListId,
                                          Item item,
                                          Long itemMobileId,
                                          Long brandMobileId,
                                          Long categoryMobileId) {
        if (shoppingItem == null)
            return null;

        MobileItem itemResponse = MobileItem.from(item, itemMobileId, brandMobileId, categoryMobileId);

        return new MobileShoppingItem(shoppingItem.getId(), shoppingItemMobileId,
                shoppingItem.getCurrency(), shoppingItem.getQuantity(), shoppingItem.getUnit(),
                shoppingItem.getUnitPrice(), shoppingListId, itemResponse);
    }

    public static MobileShoppingItem from(Tuple tuple, long shoppingListId) {
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

    public static MobileShoppingItem from(ShoppingItem shoppingItem) {
        if (shoppingItem == null)
            return null;

        MobileItem itemResponse = MobileItem.from(shoppingItem.getItem());
        String currency = shoppingItem.getCurrency();
        int unitPrice = shoppingItem.getUnitPrice();
        String unit = shoppingItem.getUnit();
        double quantity = shoppingItem.getQuantity();

        long shoppingListId = shoppingItem.getShoppingList().getId();

        return new MobileShoppingItem(shoppingItem.getId(), null,
                currency, quantity, unit, unitPrice, shoppingListId, itemResponse);
    }

    public static List<MobileShoppingItem> from(List<ShoppingItem> shoppingItems) {
        if (shoppingItems == null || shoppingItems.isEmpty())
            return null;

        return shoppingItems.stream()
                .map(MobileShoppingItem::from)
                .collect(Collectors.toList());
    }
}
