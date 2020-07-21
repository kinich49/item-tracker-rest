package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.models.front.FrontStore;

import java.util.List;

public interface StoreService {

    List<FrontStore> findLike(String name);
}
