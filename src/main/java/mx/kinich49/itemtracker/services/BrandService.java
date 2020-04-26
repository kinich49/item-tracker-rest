package mx.kinich49.itemtracker.sevices;

import mx.kinich49.itemtracker.models.Brand;

import java.util.Optional;

public interface BrandService {

    Optional<Brand> saveBrand(Brand fromRequest);

}
