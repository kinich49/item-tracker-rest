package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.database.Item;
import mx.kinich49.itemtracker.models.database.ShoppingItem;
import mx.kinich49.itemtracker.models.database.ShoppingList;
import mx.kinich49.itemtracker.models.database.User;
import mx.kinich49.itemtracker.repositories.*;
import mx.kinich49.itemtracker.requests.BaseShoppingItemRequest;
import mx.kinich49.itemtracker.requests.BaseShoppingListRequest;
import mx.kinich49.itemtracker.requests.mobile.MobileShoppingItemRequest;
import mx.kinich49.itemtracker.services.BaseDtoEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mobileDtoEntityService")
@SuppressWarnings("unused")
public class MobileDtoEntityServiceImpl extends BaseDtoEntityService {

    private final ShoppingListRepository shoppingListRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public MobileDtoEntityServiceImpl(ShoppingListRepository shoppingListRepository,
                                      StoreRepository storeRepository,
                                      BrandRepository brandRepository,
                                      CategoryRepository categoryRepository,
                                      ItemRepository itemRepository,
                                      UserRepository userRepository) {
        super(brandRepository, categoryRepository, storeRepository, userRepository);
        this.shoppingListRepository = shoppingListRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public <T extends BaseShoppingListRequest> ShoppingList from(T request) throws UserNotFoundException {
        if (request == null)
            return null;

        User user = requireExistingUser(request);
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUser(user);
        shoppingList.setShoppingDate(request.getShoppingDate());

        return shoppingList;
    }

    @Override
    public <T extends BaseShoppingItemRequest> ShoppingItem shoppingItemFrom(T request) {
        MobileShoppingItemRequest itemRequest = (MobileShoppingItemRequest) request;

        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setCurrency(request.getCurrency());
        shoppingItem.setQuantity(request.getQuantity());
        shoppingItem.setUnit(request.getUnit());
        shoppingItem.setUnitPrice(request.getUnitPrice());

        return shoppingItem;
    }

    @Override
    public <T extends BaseShoppingItemRequest> Item itemFrom(T request) {
        if (request == null)
            return null;
        return Optional.ofNullable(request.getId())
                .flatMap(itemRepository::findById)
                .orElseGet(() -> {
                    Item item = new Item();
                    item.setName(request.getName());
                    return item;
                });
    }
}
