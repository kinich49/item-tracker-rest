package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.StoreDto;
import mx.kinich49.itemtracker.repositories.StoreRepository;
import mx.kinich49.itemtracker.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Optional<List<StoreDto>> findLike(String name) {
        if (name == null || name.length() == 0)
            return Optional.empty();

        return Optional.ofNullable(storeRepository.findByNameStartsWithIgnoreCase(name))
                .map(stores ->
                        stores.stream()
                                .map(StoreDto::from)
                                .collect(Collectors.toList())
                );
    }
}
