package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.dtos.ShoppingListDto;
import mx.kinich49.itemtracker.models.*;
import mx.kinich49.itemtracker.repositories.*;
import mx.kinich49.itemtracker.requests.ShoppingListRequest;
import mx.kinich49.itemtracker.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingItemRepository shoppingItemRepository;
    private final StoreRepository storeRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ShoppingServiceImpl(ShoppingListRepository shoppingListRepository,
                               ShoppingItemRepository shoppingItemRepository,
                               StoreRepository storeRepository,
                               BrandRepository brandRepository,
                               CategoryRepository categoryRepository,
                               ItemRepository itemRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingItemRepository = shoppingItemRepository;
        this.storeRepository = storeRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Optional<ShoppingListDto> findBy(long id) {
        return shoppingListRepository.findById(id)
                .map(ShoppingListDto::from);
    }

    @Override
    public Optional<ShoppingListDto> save(ShoppingListRequest request) {
        return Optional.of(from(request))
                .map(ShoppingListDto::from);
    }

    @Override
    public List<ShoppingListDto> findBy(LocalDate date) {
        return shoppingListRepository.findByShoppingDate(date)
                .stream().parallel()
                .map(ShoppingListDto::from)
                .collect(Collectors.toList());
    }

    private ShoppingList from(ShoppingListRequest request) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setStore(from(request.getStore()));
        shoppingList.setShoppingDate(request.getShoppingDate());
        for (ShoppingListRequest.ShoppingItem itemRequest : request.getShoppingItems()) {
            Category category = from(itemRequest.getCategory());
            Brand brand = from(itemRequest.getBrand());
            ShoppingItem shoppingItem = from(itemRequest, brand, category);
            shoppingList.addShoppingItem(shoppingItem);
        }
        return shoppingListRepository.save(shoppingList);
    }

    private Brand from(ShoppingListRequest.Brand request) {
        return brandRepository.findById(request.getId())
                .orElseGet(() -> {
                    Brand brand = new Brand();
                    brand.setName(request.getName());
                    return brandRepository.save(brand);
                });
    }

    private Category from(ShoppingListRequest.Category request) {
        return categoryRepository.findById(request.getId())
                .orElseGet(() -> {
                    Category category = new Category();
                    category.setName(request.getName());
                    return categoryRepository.save(category);
                });
    }

    private ShoppingItem from(ShoppingListRequest.ShoppingItem request,
                              Brand brand, Category category) {
        Item item = itemRepository.findById(request.getItemId())
                .orElseGet(() -> {
                    Item newItem = new Item();
                    newItem.setName(request.getName());
                    newItem.setCategory(category);
                    newItem.setBrand(brand);
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

    private Store from(ShoppingListRequest.Store request) {
        return storeRepository.findById(request.getId())
                .orElseGet(() -> {
                    Store store = new Store();
                    store.setName(request.getName());
                    return storeRepository.save(store);
                });
    }
}