package mx.kinich49.itemtracker.requests.main;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.kinich49.itemtracker.requests.BaseShoppingListRequest;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class MainShoppingListRequest extends BaseShoppingListRequest {

    private StoreRequest store;
    private List<MainShoppingItemRequest> shoppingItems = new ArrayList<>();
}
