package mx.kinich49.itemtracker.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseShoppingItemRequest {

    protected Long id;
    protected String unit;
    protected double quantity;
    protected int unitPrice;
    protected String currency;
    protected String name;
}
