package mx.kinich49.itemtracker.dtos;

import lombok.Data;

@Data
public class ShoppingItemDto {

    private final long id;
    private final String name;
    private final String quantity;
    private final String unitPrice;
    private final String totalPrice;
    private final CategoryDto categoryDto;
}