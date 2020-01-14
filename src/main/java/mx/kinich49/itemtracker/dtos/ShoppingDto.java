package mx.kinich49.itemtracker.dtos;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class ShoppingDto {

    private final long id;
    private final LocalDate localDate;
    private final StoreDto store;
    private final List<ShoppingItemDto> items;
}