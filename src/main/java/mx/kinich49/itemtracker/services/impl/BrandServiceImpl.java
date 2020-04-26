package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.BrandDto;
import mx.kinich49.itemtracker.models.Brand;
import mx.kinich49.itemtracker.repositories.BrandRepository;
import mx.kinich49.itemtracker.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unused")
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandDto> findAll() {
        return brandRepository.findAll()
                .stream().parallel()
                .map(BrandDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BrandDto> findById(long id) {
        return brandRepository.findById(id)
                .map(BrandDto::from);
    }

    @Override
    public Optional<BrandDto> saveBrand(Brand fromRequest) {
        if (fromRequest == null)
            return Optional.empty();

        Optional<Brand> fromPersistence = brandRepository.findByName(fromRequest.getName());
        if (fromPersistence.isPresent())
            return Optional.empty();

        return Optional.of(brandRepository.save(fromRequest))
                .map(BrandDto::from);
    }

    @Override
    public void delete(long id) {
        brandRepository.deleteById(id);
    }
}
