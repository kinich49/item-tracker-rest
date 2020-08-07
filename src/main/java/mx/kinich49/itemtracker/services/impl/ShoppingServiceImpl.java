package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.front.FrontShoppingList;
import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.services.DtoEntityService;
import mx.kinich49.itemtracker.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final DtoEntityService dtoEntityService;
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingServiceImpl(@Qualifier("mainDtoEntityService")
                                       DtoEntityService dtoEntityService,
                               ShoppingListRepository shoppingListRepository) {
        this.dtoEntityService = dtoEntityService;
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public Optional<FrontShoppingList> findBy(long shoppingId, long userId) {
        return shoppingListRepository.findByIdAndUserId(shoppingId, userId)
                .map(FrontShoppingList::from);
    }

    @Override
    @Transactional
    public Optional<FrontShoppingList> save(MainShoppingListRequest request) throws UserNotFoundException {
        return Optional.ofNullable(dtoEntityService.from(request))
                .map(shoppingListRepository::save)
                .map(FrontShoppingList::from);
    }

    @Override
    public List<FrontShoppingList> findBy(LocalDate date, long userId) {
        return shoppingListRepository.findByShoppingDateAndUserId(date, userId)
                .stream().parallel()
                .map(FrontShoppingList::from)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBy(long shoppingListId) {
        shoppingListRepository.deleteById(shoppingListId);
    }

}