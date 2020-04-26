package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.Constants;
import mx.kinich49.itemtracker.dtos.CategoryDto;
import mx.kinich49.itemtracker.dtos.ShoppingDto;
import mx.kinich49.itemtracker.dtos.ShoppingItemDto;
import mx.kinich49.itemtracker.models.Shopping;
import mx.kinich49.itemtracker.models.ShoppingItem;
import mx.kinich49.itemtracker.repositories.ShoppingRepository;
import mx.kinich49.itemtracker.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Optional;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final ShoppingRepository repository;

    @Autowired
    public ShoppingServiceImpl(ShoppingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ShoppingDto> loadById(long id) {
        return repository.findById(id)
                .map(ShoppingDto::from);
    }

    @Override
    public Optional<ShoppingDto> save(Shopping fromRequest) {
        return Optional.of(repository.save(fromRequest))
                .map(ShoppingDto::from);
    }

}