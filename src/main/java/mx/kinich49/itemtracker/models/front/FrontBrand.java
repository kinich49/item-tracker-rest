package mx.kinich49.itemtracker.models.front;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Brand;

import java.util.List;
import java.util.stream.Collectors;

@Data
public final class FrontBrand {

    private final long id;
    private final String name;

    public static FrontBrand from(Brand brand) {
        if (brand == null)
            return null;

        return new FrontBrand(brand.getId(), brand.getName());
    }

    public static List<FrontBrand> from(List<Brand> brands) {
        if (brands == null || brands.isEmpty())
            return null;

        return brands.stream()
                .map(FrontBrand::from)
                .collect(Collectors.toList());
    }
}
