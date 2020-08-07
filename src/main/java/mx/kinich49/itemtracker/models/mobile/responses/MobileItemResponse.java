package mx.kinich49.itemtracker.models.mobile.responses;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Item;

@Data
public class MobileItemResponse {

    private final long id;
    private final Long mobileId;
    private final String name;
    private final MobileBrandResponse brand;
    private final MobileCategoryResponse category;

    public static MobileItemResponse from(Item item,
                                          Long itemMobileId,
                                          Long brandMobileId,
                                          Long categoryMobileId) {
        if (item == null)
            return null;

        MobileBrandResponse brand = MobileBrandResponse.from(item.getBrand(), brandMobileId);
        MobileCategoryResponse category = MobileCategoryResponse.from(item.getCategory(), categoryMobileId);

        return new MobileItemResponse(item.getId(), itemMobileId, item.getName(), brand, category);
    }
}
