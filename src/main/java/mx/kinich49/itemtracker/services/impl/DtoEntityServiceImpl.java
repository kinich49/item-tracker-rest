package mx.kinich49.itemtracker.services.impl;

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

    @Autowired
    public DtoEntityServiceImpl(ShoppingListRepository shoppingListRepository,
                                StoreRepository storeRepository,
                                BrandRepository brandRepository,
                                CategoryRepository categoryRepository,
                                ItemRepository itemRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.storeRepository = storeRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public ShoppingList from(ShoppingListRequest request) {
        Store store = from(request.getStore());
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setShoppingDate(request.getShoppingDate());
        store.addShoppingList(shoppingList);
        for (ShoppingListRequest.ShoppingItem itemRequest : request.getShoppingItems()) {
            Category category = from(itemRequest.getCategory());
            Brand brand = from(itemRequest.getBrand());
            ShoppingItem shoppingItem = from(itemRequest, brand, category);
            shoppingList.addShoppingItem(shoppingItem);
        }
        return shoppingListRepository.save(shoppingList);
    }

    @Override
    public Brand from(ShoppingListRequest.Brand request) {
        return brandRepository.findById(request.getId())
                .orElseGet(() -> {
                    Brand brand = new Brand();
                    brand.setName(request.getName());
                    return brandRepository.save(brand);
                });
    }

    @Override
    public Category from(ShoppingListRequest.Category request) {
        return categoryRepository.findById(request.getId())
                .orElseGet(() -> {
                    Category category = new Category();
                    category.setName(request.getName());
                    return categoryRepository.save(category);
                });
    }

    @Override
    public ShoppingItem from(ShoppingListRequest.ShoppingItem request,
                             Brand brand, Category category) {
        Item item = itemRepository.findById(request.getItemId())
                .orElseGet(() -> {
                    Item newItem = new Item();
                    newItem.setName(request.getName());
                    if (brand != null) {
                        brand.addItem(newItem);
                        brandRepository.save(brand);
                    }

                    if (category != null) {
                        category.addItem(newItem);
                        categoryRepository.save(category);
                    }
                    return itemRepository.save(newItem);
                });

        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setCurrency(request.getCurrency());
        shoppingItem.setUnit(request.getUnit());
        shoppingItem.setQuantity(request.getQuantity());
        shoppingItem.setUnitPrice(request.getUnitPrice() * 100);
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
