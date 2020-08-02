package mx.kinich49.itemtracker.models.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

@Data
public class MobileShoppingItem {

    private final long id;
    private final long itemId;
    private final long shoppingListId;
    private final int unitPrice;
    private final double quantity;
    private final String currency;

    public static MobileShoppingItem from(ShoppingItem shoppingItem) {
        if (shoppingItem == null)
            return null;

        final long id = shoppingItem.getId();
        final long itemId = shoppingItem.getItem().getId();
        final long shoppingListId = shoppingItem.getShoppingList().getId();
        final int unitPrice = shoppingItem.getUnitPrice();
        final double quantity = shoppingItem.getQuantity();
        final String currency = shoppingItem.getCurrency();

        return new MobileShoppingItem(id, itemId, shoppingListId, unitPrice, quantity, currency);
    }

    public static List<MobileShoppingItem> from(List<ShoppingItem> shoppingItems) {
        if (shoppingItems == null || shoppingItems.isEmpty())
            return null;

        List<MobileShoppingItem> mobileShoppingItems = new ArrayList<>(shoppingItems.size());
        for (ShoppingItem shoppingItem : shoppingItems) {
            mobileShoppingItems.add(MobileShoppingItem.from(shoppingItem));
        }

        return mobileShoppingItems;
    }
}
