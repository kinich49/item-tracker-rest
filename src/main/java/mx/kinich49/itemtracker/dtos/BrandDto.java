package mx.kinich49.itemtracker.dtos;

import lombok.Data;
import mx.kinich49.itemtracker.models.Brand;

@Data
public class BrandDto {

    private final long id;
    private final String name;

    public static BrandDto from(Brand brand) {
        if (brand == null)
            return null;

        return new BrandDto(brand.getId(), brand.getName());
    }
}
