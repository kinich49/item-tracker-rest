package mx.kinich49.itemtracker.models.mobile.responses;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Brand;

@Data
public class MobileBrandResponse {

    private final long id;
    private final Long mobileId;
    private final String name;

    public static MobileBrandResponse from(Brand brand, Long mobileId) {
        if (brand == null)
            return null;
        return new MobileBrandResponse(brand.getId(), mobileId, brand.getName());
    }
}
