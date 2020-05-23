package mx.kinich49.itemtracker.dtos;

import lombok.Data;
import mx.kinich49.itemtracker.models.Brand;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BrandDto {

    private final long id;
    private final String name;

    public static BrandDto from(Brand brand) {
        if (brand == null)
            return null;

        return new BrandDto(brand.getId(), brand.getName());
    }

    public static List<BrandDto> from(List<Brand> brands) {
        if (brands == null || brands.isEmpty())
            return null;

        return brands.stream()
                .map(BrandDto::from)
                .collect(Collectors.toList());
    }
}
