package mx.kinich49.itemtracker.requests.mobile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.kinich49.itemtracker.requests.BaseShoppingItemRequest;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MobileShoppingItemRequest extends BaseShoppingItemRequest {

    private Long shoppingItemMobileId;
    private Long itemMobileId;
    private MobileBrandRequest brand;
    private MobileCategoryRequest category;
}
