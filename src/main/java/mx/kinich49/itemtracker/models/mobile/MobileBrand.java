package mx.kinich49.itemtracker.models.mobile;

import lombok.Data;
import mx.kinich49.itemtracker.models.database.Brand;

import java.util.ArrayList;
import java.util.List;

@Data
public final class MobileBrand {

    private final long id;
    private final String name;

    public static MobileBrand from(Brand brand) {
        if (brand == null)
            return null;

        long id = brand.getId();
        String name = brand.getName();

        return new MobileBrand(id, name);
    }

    public static List<MobileBrand> from(List<Brand> brands) {
        if (brands == null || brands.isEmpty())
            return null;

        List<MobileBrand> mobileBrands = new ArrayList<>(brands.size());
        for (Brand brand : brands) {
            mobileBrands.add(MobileBrand.from(brand));
        }

        return mobileBrands;
    }
}
