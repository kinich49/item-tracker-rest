package mx.kinich49.itemtracker.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class SuggestionsDto {

    private final List<CategoryDto> categories;
    private final List<BrandDto> brands;
    private final List<ItemDto> items;
}
