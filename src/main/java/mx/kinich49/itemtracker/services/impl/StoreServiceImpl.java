package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.StoreDto;
import mx.kinich49.itemtracker.repositories.StoreRepository;
import mx.kinich49.itemtracker.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<StoreDto> findLike(String name) {
        if (name == null || name.length() == 0)
            return null;

        return Optional.ofNullable(storeRepository.findByNameStartsWithIgnoreCase(name))
                .filter(list -> !list.isEmpty())
                .map(StoreDto::from)
                .orElse(null);
    }
}
