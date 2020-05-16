package mx.kinich49.itemtracker.services;

import mx.kinich49.itemtracker.dtos.ShoppingListDto;
import mx.kinich49.itemtracker.requests.ShoppingListRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShoppingService {

    Optional<ShoppingListDto> findBy(long id);

    Optional<ShoppingListDto> save(ShoppingListRequest request);

    List<ShoppingListDto> findBy(LocalDate date);
}
