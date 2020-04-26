package mx.kinich49.itemtracker.sevices.impl;

import mx.kinich49.itemtracker.models.Brand;
import mx.kinich49.itemtracker.repositories.BrandRepository;
import mx.kinich49.itemtracker.sevices.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Optional<Brand> saveBrand(Brand fromRequest) {
        if (fromRequest == null)
            return Optional.empty();

        Optional<Brand> fromPersistence = brandRepository.findByName(fromRequest.getName());
        if (fromPersistence.isPresent())
            return Optional.empty();

        return Optional.of(brandRepository.save(fromRequest));
    }
}
