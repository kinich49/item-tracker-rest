package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.models.database.Brand;
import mx.kinich49.itemtracker.models.front.FrontBrand;
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
    public List<FrontBrand> findAll() {
        return brandRepository.findAll()
                .stream().parallel()
                .map(FrontBrand::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FrontBrand> findById(long id) {
        return brandRepository.findById(id)
                .map(FrontBrand::from);
    }

    //TODO revisit this
    // Probably updating a current brand will fail
    @Override
    public Optional<FrontBrand> saveBrand(Brand fromRequest) {
        if (fromRequest == null)
            return Optional.empty();

        Optional<Brand> fromPersistence = brandRepository.findByName(fromRequest.getName());
        if (fromPersistence.isPresent())
            return Optional.empty();

        return Optional.of(brandRepository.save(fromRequest))
                .map(FrontBrand::from);
    }

    @Override
    public List<FrontBrand> findLike(String name) {
        return suggestionService.findBrandsLike(name);
    }

    @Override
    public Optional<FrontBrand> updateBrand(Brand fromRequest) {
        if (fromRequest == null)
            return Optional.empty();
        Optional<Brand> optBrand = brandRepository.findById(fromRequest.getId());

        if (!optBrand.isPresent())
            return Optional.empty();

        Brand fromPersistence = optBrand.get();
        fromPersistence.setName(fromRequest.getName());

        return Optional.of(brandRepository.save(fromPersistence))
                .map(FrontBrand::from);
    }

    @Override
    public void delete(long id) {
        brandRepository.deleteById(id);
    }
}
