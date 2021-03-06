package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.models.front.FrontShoppingList;
import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.services.DtoEntityService;
import mx.kinich49.itemtracker.services.Validator;
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
    private final MainShoppingListValidatorImpl validator;
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingServiceImpl(@Qualifier("mainDtoEntityService")
                                       DtoEntityService dtoEntityService,
                               MainShoppingListValidatorImpl validator,
                               ShoppingListRepository shoppingListRepository) {
        this.dtoEntityService = dtoEntityService;
        this.validator = validator;
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public Optional<FrontShoppingList> findBy(Long shoppingId, Long userId) {
        return shoppingListRepository.findByIdAndUserId(shoppingId, userId)
                .map(FrontShoppingList::from);
    }

    @Override
    @Transactional
    public Optional<FrontShoppingList> save(MainShoppingListRequest request) throws BusinessException {
        validator.validate(request);
        return Optional.ofNullable(dtoEntityService.from(request))
                .map(shoppingListRepository::save)
                .map(FrontShoppingList::from);
    }

    @Override
    public List<FrontShoppingList> findBy(LocalDate date, Long userId) {
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