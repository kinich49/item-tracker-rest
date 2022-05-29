package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.models.front.FrontShoppingList;
import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.services.DtoEntityService;
import mx.kinich49.itemtracker.services.ShoppingService;
import mx.kinich49.itemtracker.validators.shoppinglist.ShoppingListOwnershipParameter;
import mx.kinich49.itemtracker.validators.shoppinglist.ShoppingListOwnershipValidator;
import mx.kinich49.itemtracker.validators.shoppinglist.ShoppingListParameter;
import mx.kinich49.itemtracker.validators.shoppinglist.ShoppingListValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final DtoEntityService dtoEntityService;
    private final ShoppingListValidator validator;

    private final ShoppingListOwnershipValidator ownershipValidator;
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingServiceImpl(@Qualifier("mainDtoEntityService") DtoEntityService  dtoEntityService,
                               ShoppingListValidator validator,
                               ShoppingListOwnershipValidator ownershipValidator,
                               ShoppingListRepository shoppingListRepository) {
        this.dtoEntityService = dtoEntityService;
        this.validator = validator;
        this.ownershipValidator = ownershipValidator;
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public Optional<FrontShoppingList> findBy(Long shoppingListId,
                                              UserDetails userDetails) throws BusinessException {
        ownershipValidator.validate(new ShoppingListOwnershipParameter(shoppingListId, userDetails));

        return shoppingListRepository.findById(shoppingListId)
                .map(FrontShoppingList::from);
    }

    @Override
    @Transactional
    public Optional<FrontShoppingList> save(MainShoppingListRequest request,
                                            UserDetails userDetails) throws BusinessException {
        validator.validate(new ShoppingListParameter(request));
        return Optional.ofNullable(dtoEntityService.from(request, userDetails))
                .map(shoppingListRepository::save)
                .map(FrontShoppingList::from);
    }

    @Override
    public List<FrontShoppingList> findBy(LocalDate date,
                                          UserDetails userDetails) {

        var shoppingLists = shoppingListRepository.findByShoppingDateAndUsername(date, userDetails.getUsername());

        if (shoppingLists == null || shoppingLists.isEmpty())
            return null;
        else
            return shoppingLists.stream()
                    .map(FrontShoppingList::from)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteBy(long shoppingListId, UserDetails userDetails) throws BusinessException {
        ownershipValidator.validate(new ShoppingListOwnershipParameter(shoppingListId, userDetails));
        shoppingListRepository.deleteById(shoppingListId);
    }

}