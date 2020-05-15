package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.StoreDto;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    Optional<List<StoreDto>> findLike(String name);
}
