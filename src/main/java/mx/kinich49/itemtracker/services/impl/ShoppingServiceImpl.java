package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.ShoppingListDto;
import mx.kinich49.itemtracker.models.ShoppingList;
import mx.kinich49.itemtracker.repositories.ShoppingRepository;
import mx.kinich49.itemtracker.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final ShoppingRepository repository;

    @Autowired
    public ShoppingServiceImpl(ShoppingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ShoppingListDto> loadById(long id) {
        return repository.findById(id)
                .map(ShoppingListDto::from);
    }

    @Override
    public Optional<ShoppingListDto> save(ShoppingList fromRequest) {
        return Optional.of(repository.save(fromRequest))
                .map(ShoppingListDto::from);
    }

}