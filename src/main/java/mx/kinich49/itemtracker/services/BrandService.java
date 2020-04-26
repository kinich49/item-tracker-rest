package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.BrandDto;
import mx.kinich49.itemtracker.models.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<BrandDto> findAll();

    Optional<BrandDto> findById(long id);

    Optional<BrandDto> saveBrand(Brand fromRequest);

    void delete(long id);

}
