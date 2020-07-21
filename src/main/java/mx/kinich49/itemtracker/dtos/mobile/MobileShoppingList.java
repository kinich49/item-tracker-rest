package mx.kinich49.itemtracker.dtos.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.ShoppingItem;
import mx.kinich49.itemtracker.models.ShoppingList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public final class MobileShoppingList {

    private final long id;
    private final long storeId;
    private final LocalDate shoppingDate;
    private final List<MobileShoppingItem> shoppingItems;

    public static MobileShoppingList from(ShoppingList shoppingList) {
        if (shoppingList == null)
            return null;

        long id = shoppingList.getId();
        long storeId = shoppingList.getStore().getId();
        LocalDate shoppingDate = shoppingList.getShoppingDate();
        List<MobileShoppingItem> shoppingItems = null;
        if (shoppingList.getShoppingItems() != null && !shoppingList.getShoppingItems().isEmpty()) {
            shoppingItems = new ArrayList<>();
            for (ShoppingItem shoppingItem : shoppingList.getShoppingItems()) {
                shoppingItems.add(MobileShoppingItem.from(shoppingItem));
            }
        }
        return new MobileShoppingList(id, storeId, shoppingDate, shoppingItems);
    }

    public static List<MobileShoppingList> from(List<ShoppingList> shoppingLists) {
        if (shoppingLists == null || shoppingLists.isEmpty())
            return null;

        List<MobileShoppingList> mobileShoppingLists = new ArrayList<>(shoppingLists.size());
        for (ShoppingList shoppingList : shoppingLists) {
            mobileShoppingLists.add(MobileShoppingList.from(shoppingList));
        }

        return mobileShoppingLists;
    }
}
