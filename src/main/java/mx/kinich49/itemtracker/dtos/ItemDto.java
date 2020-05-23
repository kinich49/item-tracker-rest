package mx.kinich49.itemtracker.dtos;

import lombok.Data;
import mx.kinich49.itemtracker.models.Item;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class ItemDto {
    private final long id;
    private final String name;
    private final CategoryDto category;
    private final BrandDto brand;

    public static ItemDto from(Item item) {
        if (item == null)
            return null;

        BrandDto brandDto = Optional.ofNullable(item.getBrand())
                .map(BrandDto::from)
                .orElse(null);

        CategoryDto categoryDto = Optional.ofNullable(item.getCategory())
                .map(CategoryDto::from)
                .orElse(null);

        return new ItemDto(item.getId(), item.getName(), categoryDto, brandDto);
    }

    public static List<ItemDto> from(List<Item> items) {
        if (items == null || items.isEmpty())
            return null;

        return items.stream()
                .map(ItemDto::from)
                .collect(Collectors.toList());
    }
}
