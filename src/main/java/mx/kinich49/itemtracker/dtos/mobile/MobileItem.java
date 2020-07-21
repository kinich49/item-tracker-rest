package mx.kinich49.itemtracker.dtos.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.Item;

import java.util.ArrayList;
import java.util.List;

@Data
public final class MobileItem {

    private final long id;
    private final String name;
    private final long categoryId;
    private final Long brandId;

    public static MobileItem from(Item item) {
        if (item == null)
            return null;

        long id = item.getId();
        String name = item.getName();
        long categoryId = item.getCategory().getId();
        Long brandId = null;
        if (item.getBrand() != null) {
            brandId = item.getBrand().getId();
        }

        return new MobileItem(id, name, categoryId, brandId);
    }

    public static List<MobileItem> from(List<Item> items) {
        if (items == null || items.isEmpty())
            return null;

        List<MobileItem> mobileItems = new ArrayList<>(items.size());
        for (Item item : items) {
            mobileItems.add(MobileItem.from(item));
        }

        return mobileItems;
    }
}
