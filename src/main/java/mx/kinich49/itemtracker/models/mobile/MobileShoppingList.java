package mx.kinich49.itemtracker.models.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.ShoppingList;
import mx.kinich49.itemtracker.models.database.Store;
import org.javatuples.Tuple;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MobileShoppingList {

    private final long remoteId;
    private final Long mobileId;
    private final LocalDate shoppingDate;
    private final MobileStore store;
    private final List<MobileShoppingItem> shoppingItems;

    public static MobileShoppingList from(ShoppingList shoppingList,
                                          Long shoppingListMobileId,
                                          Store store,
                                          Long storeMobileId,
                                          List<Tuple> shoppingItems) {

        long shoppingListId = shoppingList.getId();
        LocalDate shoppingDate = shoppingList.getShoppingDate();
        MobileStore storeResponse = MobileStore.from(store, storeMobileId);

        List<MobileShoppingItem> shoppingItemResponses = shoppingItems.stream()
                .map(tuple -> MobileShoppingItem.from(tuple, shoppingListId))
                .collect(Collectors.toList());

        return new MobileShoppingList(shoppingListId, shoppingListMobileId,
                shoppingDate, storeResponse, shoppingItemResponses);
    }

    public static MobileShoppingList from(ShoppingList shoppingList) {
        if (shoppingList == null)
            return null;

        MobileStore storeResponse = MobileStore.from(shoppingList.getStore());
        LocalDate shoppingDate = shoppingList.getShoppingDate();

        return new MobileShoppingList(shoppingList.getId(), null, shoppingDate,
                storeResponse, null);
    }

    public static List<MobileShoppingList> from(List<ShoppingList> shoppingLists) {
        if (shoppingLists == null || shoppingLists.isEmpty())
            return null;

        return shoppingLists.stream()
                .map(MobileShoppingList::from)
                .collect(Collectors.toList());
    }
}
