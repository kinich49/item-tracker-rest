package mx.kinich49.itemtracker.dtos;

import java.util.List;
import lombok.Data;

@Data
public class ShoppingDto {

    private final long id;
    private final List<ShoppingItemDto> items;
    private final StoreDto store;

}