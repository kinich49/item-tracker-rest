package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.BrandDto;
import mx.kinich49.itemtracker.models.Brand;
import mx.kinich49.itemtracker.repositories.BrandRepository;
import mx.kinich49.itemtracker.services.BrandService;
import mx.kinich49.itemtracker.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unused")
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final SuggestionService suggestionService;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository,
                            SuggestionService suggestionService) {
        this.brandRepository = brandRepository;
        this.suggestionService = suggestionService;
    }

    //TODO revisit this
    // Probably map to Dto can be done
    // via jpql
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

    //TODO revisit this
    // Probably updating a current brand will fail
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
    public List<BrandDto> findLike(String name) {
        return suggestionService.findBrandsLike(name);
    }

    @Override
    public void delete(long id) {
        brandRepository.deleteById(id);
    }
}
