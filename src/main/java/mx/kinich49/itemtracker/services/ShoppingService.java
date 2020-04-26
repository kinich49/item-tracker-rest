package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.ShoppingListDto;
import mx.kinich49.itemtracker.models.ShoppingList;

import java.util.Optional;

public interface ShoppingService {

    Optional<ShoppingListDto> loadById(long id);

    Optional<ShoppingListDto> save(ShoppingList fromRequest);
}
