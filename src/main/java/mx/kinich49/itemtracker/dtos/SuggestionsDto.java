package mx.kinich49.itemtracker.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class SuggestionsDto {

    List<CategoryDto> categories;
    List<BrandDto> brands;
    List<ItemDto> items;
}