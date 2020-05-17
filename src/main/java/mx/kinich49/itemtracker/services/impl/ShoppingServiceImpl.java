package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.ShoppingListDto;
import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import mx.kinich49.itemtracker.requests.ShoppingListRequest;
import mx.kinich49.itemtracker.services.ShoppingService;
import mx.kinich49.itemtracker.services.TransformShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final TransformShoppingListService transformShoppingListService;
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingServiceImpl(TransformShoppingListService transformShoppingListService,
                               ShoppingListRepository shoppingListRepository) {
        this.transformShoppingListService = transformShoppingListService;
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public Optional<ShoppingListDto> findBy(long id) {
        return shoppingListRepository.findById(id)
                .map(ShoppingListDto::from);
    }

    @Override
    @Transactional
    public Optional<ShoppingListDto> save(ShoppingListRequest request) {
        return Optional.of(transformShoppingListService.from(request))
                .map(shoppingListRepository::save)
                .map(ShoppingListDto::from);
    }

    @Override
    public List<ShoppingListDto> findBy(LocalDate date) {
        return shoppingListRepository.findByShoppingDate(date)
                .stream().parallel()
                .map(ShoppingListDto::from)
                .collect(Collectors.toList());
    }
}