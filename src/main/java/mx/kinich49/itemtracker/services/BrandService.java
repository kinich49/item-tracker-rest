package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.models.database.Brand;
import mx.kinich49.itemtracker.models.front.FrontBrand;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<FrontBrand> findAll();

    Optional<FrontBrand> findById(Long id);

    Optional<FrontBrand> saveBrand(Brand fromRequest);

    List<FrontBrand> findLike(String name);

    Optional<FrontBrand> updateBrand(Brand dto);

    void delete(long id);

}
