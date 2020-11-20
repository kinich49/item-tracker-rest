package mx.kinich49.itemtracker.requests.main;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.kinich49.itemtracker.requests.BaseShoppingItemRequest;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MainShoppingItemRequest extends BaseShoppingItemRequest {

    protected CategoryRequest category;
    protected BrandRequest brand;
}