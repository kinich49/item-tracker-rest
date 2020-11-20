package mx.kinich49.itemtracker.models.front;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Item;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public final class FrontItem {
    private final long id;
    private final String name;
    private final FrontCategory category;
    private final FrontBrand brand;

    public static FrontItem from(Item item) {
        if (item == null)
            return null;

        FrontBrand frontBrand = Optional.ofNullable(item.getBrand())
                .map(FrontBrand::from)
                .orElse(null);

        FrontCategory frontCategory = Optional.ofNullable(item.getCategory())
                .map(FrontCategory::from)
                .orElse(null);

        return new FrontItem(item.getId(), item.getName(), frontCategory, frontBrand);
    }

    public static List<FrontItem> from(List<Item> items) {
        if (items == null || items.isEmpty())
            return null;

        return items.stream()
                .map(FrontItem::from)
                .collect(Collectors.toList());
    }
}
