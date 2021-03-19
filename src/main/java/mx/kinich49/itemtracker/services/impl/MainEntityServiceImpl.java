package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.models.database.*;
import mx.kinich49.itemtracker.repositories.*;
import mx.kinich49.itemtracker.requests.BaseShoppingItemRequest;
import mx.kinich49.itemtracker.requests.BaseShoppingListRequest;
import mx.kinich49.itemtracker.requests.main.MainShoppingItemRequest;
import mx.kinich49.itemtracker.requests.main.MainShoppingListRequest;
import mx.kinich49.itemtracker.services.BaseDtoEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mainDtoEntityService")
public class MainEntityServiceImpl extends BaseDtoEntityService {

    private final ShoppingListRepository shoppingListRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public MainEntityServiceImpl(BrandRepository brandRepository,
                                 CategoryRepository categoryRepository,
                                 StoreRepository storeRepository,
                                 ShoppingListRepository shoppingListRepository,
                                 ItemRepository itemRepository,
                                 UserRepository userRepository) {
        super(brandRepository, categoryRepository, storeRepository, userRepository);
        this.shoppingListRepository = shoppingListRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public <T extends BaseShoppingListRequest> ShoppingList from(T request) {
        if (request == null)
            return null;

        User user = userRepository.getOne(request.getUserId());

        MainShoppingListRequest shoppingListRequest = (MainShoppingListRequest) request;

        Store store = from(shoppingListRequest.getStore());
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUser(user);
        shoppingList.setShoppingDate(request.getShoppingDate());
        store.addShoppingList(shoppingList);

        for (MainShoppingItemRequest itemRequest : shoppingListRequest.getShoppingItems()) {
            Category category = from(itemRequest.getCategory());
            Brand brand = from(itemRequest.getBrand());
            ShoppingItem shoppingItem = from(itemRequest, brand, category);
            shoppingList.addShoppingItem(shoppingItem);
        }

        return shoppingList;
    }

    @Override
    public <T extends BaseShoppingItemRequest> ShoppingItem shoppingItemFrom(T request) {
        throw new IllegalStateException("Method not implemented");
    }

    @Override
    public <T extends BaseShoppingItemRequest> Item itemFrom(T request) {
        throw new IllegalStateException("Method not implemented");
    }

    private ShoppingItem from(MainShoppingItemRequest itemRequest,
                              Brand brand,
                              Category category) {

        Item item = Optional.ofNullable(itemRequest.getId())
                .flatMap(itemRepository::findById)
                .orElseGet(() -> {
                    Item newItem = new Item();
                    newItem.setName(itemRequest.getName());
                    if (brand != null) {
                        brand.addItem(newItem);
                    }

                    if (category != null) {
                        category.addItem(newItem);
                    }
                    return itemRepository.save(newItem);
                });

        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setCurrency(itemRequest.getCurrency());
        shoppingItem.setUnit(itemRequest.getUnit());
        shoppingItem.setQuantity(itemRequest.getQuantity());
        shoppingItem.setUnitPrice(itemRequest.getUnitPrice() * 100);

        item.addShoppingItem(shoppingItem);

        return shoppingItem;
    }
}
