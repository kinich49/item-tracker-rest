package mx.kinich49.itemtracker.models.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Item;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MobileItem {

    private final long remoteId;
    private final Long mobileId;
    private final String name;
    private final MobileBrand brand;
    private final MobileCategory category;

    public static MobileItem from(Item item,
                                  Long itemMobileId,
                                  Long brandMobileId,
                                  Long categoryMobileId) {
        if (item == null)
            return null;

        MobileBrand brand = MobileBrand.from(item.getBrand(), brandMobileId);
        MobileCategory category = MobileCategory.from(item.getCategory(), categoryMobileId);

        return new MobileItem(item.getId(), itemMobileId, item.getName(), brand, category);
    }

    public static MobileItem from(Item item) {
        if (item == null)
            return null;

        return from(item, null, null, null);
    }

    public static List<MobileItem> from(List<Item> items) {
        if (items == null || items.isEmpty())
            return null;

        return items.stream()
                .map(MobileItem::from)
                .collect(Collectors.toList());
    }
}
