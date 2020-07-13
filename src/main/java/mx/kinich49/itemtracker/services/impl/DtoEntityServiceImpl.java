package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.*;
import mx.kinich49.itemtracker.repositories.*;
import mx.kinich49.itemtracker.requests.ShoppingListRequest;
import mx.kinich49.itemtracker.services.DtoEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DtoEntityServiceImpl implements DtoEntityService {

    private final ShoppingListRepository shoppingListRepository;
    private final StoreRepository storeRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public DtoEntityServiceImpl(ShoppingListRepository shoppingListRepository,
                                StoreRepository storeRepository,
                                BrandRepository brandRepository,
                                CategoryRepository categoryRepository,
                                ItemRepository itemRepository,
                                UserRepository userRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.storeRepository = storeRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ShoppingList from(ShoppingListRequest request) throws UserNotFoundException {
        if (request == null)
            return null;

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        Store store = from(request.getStore());
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUser(user);
        shoppingList.setShoppingDate(request.getShoppingDate());
        store.addShoppingList(shoppingList);
        for (ShoppingListRequest.ShoppingItem itemRequest : request.getShoppingItems()) {
            Category category = from(itemRequest.getCategory());
            Brand brand = from(itemRequest.getBrand());
            ShoppingItem shoppingItem = from(itemRequest, brand, category);
            shoppingList.addShoppingItem(shoppingItem);
        }
        return shoppingList;
    }

    @Override
    public Brand from(ShoppingListRequest.Brand request) {
        return brandRepository.findById(request.getId())
                .orElseGet(() -> {
                    Brand brand = new Brand();
                    brand.setName(request.getName());
                    return brand;
                });
    }

    @Override
    public Category from(ShoppingListRequest.Category request) {
        return categoryRepository.findById(request.getId())
                .orElseGet(() -> {
                    Category category = new Category();
                    category.setName(request.getName());
                    return category;
                });
    }

    @Override
    public ShoppingItem from(ShoppingListRequest.ShoppingItem itemRequest,
                             Brand brand, Category category) {
        Item item = itemRepository.findById(itemRequest.getId())
                .orElseGet(() -> {
                    Item newItem = new Item();
                    newItem.setName(itemRequest.getName());
                    if (brand != null) {
                        brand.addItem(newItem);
                    }

                    if (category != null) {
                        category.addItem(newItem);
                    }
                    return newItem;
                });

        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setCurrency(itemRequest.getCurrency());
        shoppingItem.setUnit(itemRequest.getUnit());
        shoppingItem.setQuantity(itemRequest.getQuantity());
        shoppingItem.setUnitPrice(itemRequest.getUnitPrice() * 100);
        item.addShoppingItem(shoppingItem);
        return shoppingItem;
    }

    @Override
    public Store from(ShoppingListRequest.Store request) {
        return storeRepository.findById(request.getId())
                .orElseGet(() -> {
                    Store store = new Store();
                    store.setName(request.getName());
                    return storeRepository.save(store);
                });
    }
}
