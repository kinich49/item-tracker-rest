package mx.kinich49.itemtracker.services.impl;

import mx.kinich49.itemtracker.exceptions.UserNotFoundException;
import mx.kinich49.itemtracker.models.database.*;
import mx.kinich49.itemtracker.models.mobile.responses.MobileShoppingListResponse;
import mx.kinich49.itemtracker.repositories.ShoppingItemRepository;
import mx.kinich49.itemtracker.repositories.ShoppingListRepository;
import mx.kinich49.itemtracker.repositories.StoreRepository;
import mx.kinich49.itemtracker.requests.mobile.MobileShoppingItemRequest;
import mx.kinich49.itemtracker.requests.mobile.MobileShoppingListRequest;
import mx.kinich49.itemtracker.services.DtoEntityService;
import mx.kinich49.itemtracker.services.MobileShoppingService;
import org.javatuples.Sextet;
import org.javatuples.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileShoppingServiceImpl implements MobileShoppingService {

    private final ShoppingListRepository shoppingListRepository;
    private final StoreRepository storeRepository;
    private final ShoppingItemRepository shoppingItemRepository;
    private final DtoEntityService dtoEntityService;

    @Autowired
    public MobileShoppingServiceImpl(ShoppingListRepository shoppingListRepository,
                                     StoreRepository storeRepository,
                                     ShoppingItemRepository shoppingItemRepository,
                                     @Qualifier("mobileDtoEntityService")
                                             DtoEntityService dtoEntityService) {
        this.shoppingListRepository = shoppingListRepository;
        this.storeRepository = storeRepository;
        this.shoppingItemRepository = shoppingItemRepository;
        this.dtoEntityService = dtoEntityService;
    }

    @Override
    public MobileShoppingListResponse save(MobileShoppingListRequest request) throws UserNotFoundException {

        ShoppingList shoppingList = dtoEntityService.from(request);
        Long shoppingListMobileId = request.getMobileId();
        Store store = dtoEntityService.from(request.getStore());
        Long storeMobileId = request.getStore().getMobileId();

        List<Tuple> tuples = new ArrayList<>(request.getShoppingItems().size());
        for (MobileShoppingItemRequest shoppingItemRequest : request.getShoppingItems()) {

            Brand brand = dtoEntityService.from(shoppingItemRequest.getBrand());
            Category category = dtoEntityService.from(shoppingItemRequest.getCategory());
            Item item = dtoEntityService.itemFrom(shoppingItemRequest);
            ShoppingItem shoppingItem = dtoEntityService.shoppingItemFrom(shoppingItemRequest);

            brand.addItem(item);
            category.addItem(item);
            item.addShoppingItem(shoppingItem);

            shoppingItemRepository.save(shoppingItem);
            Long shoppingItemMobileId = shoppingItemRequest.getShoppingItemMobileId();
            Long itemMobileId = shoppingItemRequest.getItemMobileId();
            Long brandMobileId = shoppingItemRequest.getBrand().getMobileId();
            Long categoryMobileId = shoppingItemRequest.getCategory().getMobileId();
            Tuple tuple = new Sextet<>(shoppingItem, shoppingItemMobileId, item,
                    itemMobileId, brandMobileId, categoryMobileId);
            tuples.add(tuple);
        }

        store.addShoppingList(shoppingList);
        shoppingList = shoppingListRepository.save(shoppingList);

        return MobileShoppingListResponse.from(shoppingList, shoppingListMobileId, store, storeMobileId, tuples);
    }


}
