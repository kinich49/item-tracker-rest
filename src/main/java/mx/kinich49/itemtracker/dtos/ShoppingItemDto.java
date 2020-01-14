package mx.kinich49.itemtracker.dtos;

import lombok.Data;

@Data
public class ShoppingItemDto {

    private final long id;
    private final double quantity;
    private final String measure;
    private final double unitPrice;
    private final double totalPrice;
    private final CategoryDto categoryDto;
}