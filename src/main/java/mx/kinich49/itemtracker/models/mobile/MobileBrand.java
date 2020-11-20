package mx.kinich49.itemtracker.models.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Brand;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MobileBrand {

    private final long remoteId;
    private final Long mobileId;
    private final String name;

    public static MobileBrand from(Brand brand, Long mobileId) {
        if (brand == null)
            return null;
        return new MobileBrand(brand.getId(), mobileId, brand.getName());
    }

    public static MobileBrand from(Brand brand) {
        if (brand == null)
            return null;
        return new MobileBrand(brand.getId(), null, brand.getName());
    }

    public static List<MobileBrand> from(List<Brand> brands) {
        if (brands == null || brands.isEmpty())
            return null;

        return brands.stream()
                .map(MobileBrand::from)
                .collect(Collectors.toList());
    }
}
