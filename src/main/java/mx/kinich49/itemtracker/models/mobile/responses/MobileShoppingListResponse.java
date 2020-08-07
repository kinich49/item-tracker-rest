package mx.kinich49.itemtracker.models.mobile.responses;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.ShoppingList;
import mx.kinich49.itemtracker.models.database.Store;
import org.javatuples.Tuple;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MobileShoppingListResponse {

    private final long id;
    private final Long mobileId;
    private final MobileStoreResponse store;
    private final List<MobileShoppingItemResponse> shoppingItems;

    public static MobileShoppingListResponse from(ShoppingList shoppingList,
                                                  Long shoppingListMobileId,
                                                  Store store,
                                                  Long storeMobileId,
                                                  List<Tuple> shoppingItems) {

        long shoppingListId = shoppingList.getId();
        MobileStoreResponse storeResponse = MobileStoreResponse.from(store, storeMobileId);

        List<MobileShoppingItemResponse> shoppingItemResponses = shoppingItems.stream()
                .map(tuple -> MobileShoppingItemResponse.from(tuple, shoppingListId))
                .collect(Collectors.toList());

        return new MobileShoppingListResponse(shoppingListId, shoppingListMobileId,
                storeResponse, shoppingItemResponses);
    }
}
