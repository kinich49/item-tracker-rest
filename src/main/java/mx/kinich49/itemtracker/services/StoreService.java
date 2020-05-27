package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.StoreDto;

import java.util.List;

public interface StoreService {

    List<StoreDto> findLike(String name);
}
