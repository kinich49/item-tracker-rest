package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.ShoppingDto;
import mx.kinich49.itemtracker.models.Shopping;

import java.util.Optional;

public interface ShoppingService {

    Optional<ShoppingDto> loadById(long id);

    Optional<ShoppingDto> save(Shopping fromRequest);
}
