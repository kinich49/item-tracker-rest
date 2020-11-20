package mx.kinich49.itemtracker.requests.mobile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.kinich49.itemtracker.requests.BaseShoppingListRequest;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
public class MobileShoppingListRequest extends BaseShoppingListRequest {

    private Long mobileId;
    private MobileStoreRequest store;
    protected List<MobileShoppingItemRequest> shoppingItems;
}
